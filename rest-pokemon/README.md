# rest-pokemon

### Module de gestion de la persistence des pokemons.

---

- Ajout de l'extension pour les metrics
```shell
  mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-micrometer-registry-prometheus"
```

- Ajout de l'extension OpenApi
```shell
  mvn quarkus:add-extension -Dextensions="io.quarkus:quarkus-smallrye-openapi"
```

---

- Build de l'application en profil h2
```shell
  mvn clean package -Dquarkus.profile=h2
```

- Start de l'application en profil h2 sans debug
```shell
  mvn clean package -DskipTests -Dquarkus.profile=h2 quarkus:dev
```

- Start de l'application en profil h2 avec debug sur le port 5010
```shell
  mvn clean package -DskipTests -Ddebug=5010 -Dquarkus.profile=h2 quarkus:dev
```

- Accès à la DevUI
  - http://localhost:8010/q/dev-ui/

- Accès à l'interface d'admin
  - http://localhost:8010/admin/home