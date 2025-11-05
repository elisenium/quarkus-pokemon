# quarkus-pokemon

Ce dépôt contient un petit atelier Quarkus / frontend permettant de manipuler des Pokémons.

Composants principaux
- `rest-pokemon` : API Quarkus qui expose les données et opérations sur les Pokémons (par défaut port 8010)
- `rest-fight`  : API Quarkus qui gère la logique de combat entre deux Pokémons (par défaut utilisé en dev sur 8030)
- `pokemon-ui`  : frontend (Vite) qui consomme les deux APIs et affiche les Pokémons

Ce README explique comment configurer et lancer le projet en local (dev), comment builder pour la production et quelques bonnes pratiques.

Prérequis
- Java 17+ (ou la version requise par vos modules Quarkus)
- Maven (ou utilisez les wrappers `mvn` fournis)
- Node.js 16+ et npm

Organisation du dépôt
- `rest-pokemon/` — backend Pokémon (Quarkus)
- `rest-fight/` — backend combats (Quarkus)
- `pokemon-ui/` — frontend Vite

Configuration locale

Le frontend lit des variables d'environnement pour configurer les URLs des APIs et le port de développement. Par exemple, les variables suivantes sont lues par `vite.config.js` :

- `VITE_POKEMON_BASE` ou `POKEMON_BASE` — URL du backend rest-pokemon (ex: `http://localhost:8010`)
- `VITE_FIGHT_BASE` ou `FIGHT_BASE` — URL du backend rest-fight (ex: `http://localhost:8030`)
- `VITE_DEV_SERVER_PORT` ou `DEV_SERVER_PORT` — port du serveur dev Vite (ex: `8020`)

Pour créer un fichier d'environnement local pour le frontend :
```bash
cd pokemon-ui
cp ../.env.example .env
# puis éditez .env et adaptez les URL si nécessaire
```

Lancer le projet en développement (recommandé : ouvrir 3 terminaux)

1) Démarrer `rest-pokemon`
```bash
# depuis la racine du repo (commande que tu utilises)
mvn -f rest-pokemon/pom.xml clean package -Dquarkus.profile=h2 quarkus:dev

# ou depuis le dossier rest-pokemon
cd rest-pokemon
mvn clean package -Dquarkus.profile=h2 quarkus:dev
```

2) Démarrer `rest-fight` (doit correspondre à la cible configurée dans Vite)
```bash
# depuis la racine ou depuis rest-fight, commande que tu utilises
mvn clean package quarkus:dev

# ou depuis le dossier rest-fight
cd rest-fight
mvn clean package quarkus:dev
```

3) Démarrer `pokemon-ui` (frontend)
```bash
cd pokemon-ui
npm install
# s'assurer que .env est en place (VITE_FIGHT_BASE/VITE_POKEMON_BASE)
npm run dev
```

Vérifications rapides
- Tester les endpoints backend :
```bash
curl -v http://localhost:8010/pokemons/list
curl -v http://localhost:8030/fighters/show
```
- Si vous avez une erreur `ECONNREFUSED` dans Vite pour `/fighters` ou `/pokemons` : le backend n'est pas démarré, ou le port configuré ne correspond pas. Vérifiez `pokemon-ui/vite.config.js` et votre `.env`.

Télécharger les images (si nécessaire)
- Le frontend contient un script pour télécharger des images statiques dans `pokemon-ui/public/images` :
```bash
cd pokemon-ui
npm run download-images
```

Tests
- Backends (Maven/Quarkus) :
```bash
mvn -f rest-pokemon/pom.xml test
mvn -f rest-fight/pom.xml test
```
- Frontend (Vitest) :
```bash
cd pokemon-ui
npm test
# ou en watch
npx vitest
```

Build et déploiement

Backend (package et run)
```bash
# build
mvn -f rest-pokemon/pom.xml package
# exécuter le jar Quarkus produit
java -jar rest-pokemon/target/quarkus-app/quarkus-run.jar -Dquarkus.http.port=8010

# idem pour rest-fight
mvn -f rest-fight/pom.xml package
java -jar rest-fight/target/quarkus-app/quarkus-run.jar -Dquarkus.http.port=8030
```

Frontend (build statique)
```bash
cd pokemon-ui
npm run build
# fichiers statiques dans pokemon-ui/dist/
# servir dist/ via nginx ou tout serveur statique
npm run preview
```


Dépannage courant

- `ECONNREFUSED` sur `/fighters/show` : vérifier que `rest-fight` tourne et écoute sur le port configuré par `vite.config.js` ou `.env`.
- Images manquantes : vérifier que `pokemon-ui/public/images/<id>.png` existe ou que le backend renvoie `imageUrl` correct.
- Tests frontend qui échouent parce que le payload attendu diffère : `startFight` attend `fighter1` et `fighter2` avec des `id`.

Options avancées (facultatif)
- Ajouter un `docker-compose.yml` pour lancer les trois services ensemble en local.
- Ajouter une action GitHub CI pour builder et tester automatiquement.
