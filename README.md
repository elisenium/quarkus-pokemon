# quarkus-pokemon

This repository contains a small Quarkus + frontend workshop to manage Pokémon.

## Main components
- `rest-pokemon`: Quarkus API that exposes Pokémon data and operations (default port: 8010)
- `rest-fight`: Quarkus API that handles fight logic between two Pokémon (commonly used on port 8030 during development)
- `pokemon-ui`: Vite-based frontend that consumes both APIs and displays the Pokémon

This README explains how to configure and run the project locally (development), how to build for production and some common troubleshooting tips.

## Prerequisites
- Java 17+ (or the version required by the Quarkus modules)
- Maven (or use the included wrapper `./mvnw`)
- Node.js 16+ and npm

## Repository layout
- `rest-pokemon/` — Pokémon backend (Quarkus)
- `rest-fight/` — fight backend (Quarkus)
- `pokemon-ui/` — frontend (Vite)

## Local configuration

The frontend reads environment variables to configure API URLs and the dev server port. `vite.config.js` recognizes (for example):

- `VITE_POKEMON_BASE` or `POKEMON_BASE` — URL for the rest-pokemon backend (e.g. `http://localhost:8010`)
- `VITE_FIGHT_BASE` or `FIGHT_BASE` — URL for the rest-fight backend (e.g. `http://localhost:8030`)
- `VITE_DEV_SERVER_PORT` or `DEV_SERVER_PORT` — port for the Vite dev server (e.g. `8020`)

To create a local environment file for the frontend:
```bash
cd pokemon-ui
cp ../.env.example .env
# then edit .env and adjust the URLs as needed
```

Run the project in development (recommended: open three terminals)

1) Start `rest-pokemon`
```bash
# from the repository root (your preferred command)
mvn -f rest-pokemon/pom.xml clean package -Dquarkus.profile=h2 quarkus:dev

# or from the rest-pokemon folder
cd rest-pokemon
mvn clean package -Dquarkus.profile=h2 quarkus:dev
```

2) Start `rest-fight` (must match the target configured in Vite)
```bash
# from the repository root or from rest-fight (your preferred command)
mvn -f rest-fight/pom.xml clean package quarkus:dev

# or from the rest-fight folder
cd rest-fight
mvn clean package quarkus:dev
```

3) Start `pokemon-ui` (frontend)
```bash
cd pokemon-ui
npm install
# ensure .env is present (VITE_FIGHT_BASE / VITE_POKEMON_BASE)
npm run dev
```

Quick checks

- Test the backend endpoints:
```bash
curl -v http://localhost:8010/pokemons/list
curl -v http://localhost:8030/fighters/show
```
- If you see `ECONNREFUSED` in Vite for `/fighters` or `/pokemons`, the backend is not running or the port does not match the configuration. Check `pokemon-ui/vite.config.js` and your `.env`.

Download images (if needed)

- The frontend includes a script to download static images into `pokemon-ui/public/images`:
```bash
cd pokemon-ui
npm run download-images
```

## Tests

- Backends (Maven/Quarkus):
```bash
mvn -f rest-pokemon/pom.xml test
mvn -f rest-fight/pom.xml test
```
- Frontend (Vitest):
```bash
cd pokemon-ui
npm test
# or in watch mode
npx vitest
```

## Build and deployment

Backend (package and run)
```bash
# build
mvn -f rest-pokemon/pom.xml package
# run the produced Quarkus jar
java -jar rest-pokemon/target/quarkus-app/quarkus-run.jar -Dquarkus.http.port=8010

# same for rest-fight
mvn -f rest-fight/pom.xml package
java -jar rest-fight/target/quarkus-app/quarkus-run.jar -Dquarkus.http.port=8030
```

Frontend (static build)
```bash
cd pokemon-ui
npm run build
# the static files will be in pokemon-ui/dist/
# serve dist/ with nginx or any static server
npm run preview
```

## Troubleshooting

- `ECONNREFUSED` on `/fighters/show`: verify that `rest-fight` is running and listening on the port configured in `vite.config.js` or `.env`.
- Missing images: check that `pokemon-ui/public/images/<id>.png` exists or that the backend returns a correct `imageUrl`.
- Frontend tests failing because the payload differs: `startFight` expects `fighter1` and `fighter2` objects that include `id` values.

## Advanced options (optional)

- Add a `docker-compose.yml` to run all three services together locally.
- Add a GitHub Actions workflow to build and test automatically.
