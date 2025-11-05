const fs = require('fs');
const path = require('path');
const https = require('https');

// Liste des Pokémon à télécharger (IDs 1-12)
const pokemonIds = [
  { id: 1, name: 'jolteon' },      // 135 Voltali
  { id: 2, name: 'pikachu' },      // 25 Pikachu
  { id: 3, name: 'mewtwo' },       // 150 Mewtwo
  { id: 4, name: 'charizard' },    // 6 Dracaufeu
  { id: 5, name: 'kingler' },      // 99 Krabboss
  { id: 6, name: 'eevee' },        // 133 Evoli
  { id: 7, name: 'snorlax' },      // 143 Ronflex
  { id: 8, name: 'weepinbell' },   // 70 Boustiflor
  { id: 9, name: 'blastoise' },    // 9 Tortank
  { id: 10, name: 'electrode' },    // 101 Electrode
  { id: 11, name: 'golem' },       // 76 Grolem
  { id: 12, name: 'gyarados' }     // 130 Léviator
];

const officialArtworkUrl = (pokemonName) => 
  `https://img.pokemondb.net/artwork/large/${pokemonName}.jpg`;

const downloadImage = (url, filepath) => {
  return new Promise((resolve, reject) => {
    https.get(url, (res) => {
      if (res.statusCode === 200) {
        res.pipe(fs.createWriteStream(filepath))
           .on('error', reject)
           .once('close', () => resolve(filepath));
      } else {
        res.resume();
        reject(new Error(`Request Failed With a Status Code: ${res.statusCode}`));
      }
    });
  });
};

async function downloadAllImages() {
  const imagesDir = path.join(__dirname, '..', 'public', 'images');
  
  // Créer le dossier images s'il n'existe pas
  if (!fs.existsSync(imagesDir)) {
    fs.mkdirSync(imagesDir, { recursive: true });
  }

  for (const pokemon of pokemonIds) {
    const filepath = path.join(imagesDir, `${pokemon.id}.png`);
    const url = officialArtworkUrl(pokemon.name);
    
    console.log(`Téléchargement de ${pokemon.name}...`);
    try {
      await downloadImage(url, filepath);
      console.log(`✅ ${pokemon.name} téléchargé avec succès`);
    } catch (error) {
      console.error(`❌ Erreur lors du téléchargement de ${pokemon.name}:`, error);
    }
  }
}

downloadAllImages().catch(console.error);