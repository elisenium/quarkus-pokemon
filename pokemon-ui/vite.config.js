import { defineConfig, loadEnv } from 'vite';

// Utilise loadEnv pour lire les variables d'environnement en fonction du mode
export default ({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '');

  // Priorité : variables VITE_* (visibles au client) ou fallback vers des noms sans préfixe
  const FIGHT_TARGET = env.VITE_FIGHT_BASE || env.FIGHT_BASE || 'http://localhost:8030';
  const POKEMON_TARGET = env.VITE_POKEMON_BASE || env.POKEMON_BASE || 'http://localhost:8010';
  const DEV_PORT = Number(env.VITE_DEV_SERVER_PORT || env.DEV_SERVER_PORT || 8020);

  return defineConfig({
    server: {
      // Démarrer le serveur de dev sur le port configuré (8020 par défaut)
      port: DEV_PORT,
      proxy: {
        '/fighters': {
          target: FIGHT_TARGET,
          changeOrigin: true
        },
        '/pokemons': {
          target: POKEMON_TARGET,
          changeOrigin: true
        }
      }
    },
    // Sert le dossier public à la racine
    publicDir: 'public'
  });
};
