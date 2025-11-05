import { getFighters, startFight } from './api.js';
import { PokemonCard } from './components/PokemonCard.js';
import { BattleAnimation, startBattleAnimation } from './components/BattleAnimation.js';

export function createApp(container) {
  container.innerHTML = `
    <div class="app">
      <h1>Pokémon Arena</h1>
      <div id="fighters" class="fighters">Chargement...</div>
      <div class="controls">
        <button id="refresh">Nouveaux combattants</button>
        <button id="fight">Combattre</button>
      </div>
      <div id="result" class="result"></div>
      ${BattleAnimation()}
    </div>
  `;

  const fightersEl = container.querySelector('#fighters');
  const resultEl = container.querySelector('#result');
  const refreshBtn = container.querySelector('#refresh');
  const fightBtn = container.querySelector('#fight');

  async function loadFighters() {
    fightersEl.textContent = 'Chargement...';
    resultEl.textContent = '';
    try {
      const fighters = await getFighters();
      // Expect fighters to be an object or array with two combatants
      if (Array.isArray(fighters) && fighters.length >= 2) {
        fightersEl.innerHTML = `
          <div class="fighter">
            <h2>${fighters[0].name || 'Pokémon 1'}</h2>
            <p>${fighters[0].type || ''}</p>
          </div>
          <div class="vs">VS</div>
          <div class="fighter">
            <h2>${fighters[1].name || 'Pokémon 2'}</h2>
            <p>${fighters[1].type || ''}</p>
          </div>
        `;
        // Store current fighters for fight
        fightersEl.dataset.current = JSON.stringify([fighters[0], fighters[1]]);
      } else {
        fightersEl.textContent = 'Réponse inattendue du service rest-fight';
      }
    } catch (err) {
      fightersEl.textContent = `Erreur lors du chargement des combattants: ${err.message}`;
      console.error('Error loading fighters:', err);
    }
  }

  refreshBtn.addEventListener('click', loadFighters);

  fightBtn.addEventListener('click', async () => {
    resultEl.textContent = 'Démarrage du combat...';
    try {
      const current = fightersEl.dataset.current ? JSON.parse(fightersEl.dataset.current) : null;
      console.debug('[APP] Current fighters:', current);
      // Modifions légèrement le format du payload pour correspondre à ce que le backend attend probablement
      const payload = current && Array.isArray(current) ? {
        fighter1: current[0],
        fighter2: current[1]
      } : undefined;
      console.debug('[APP] Fight payload:', payload);
      const result = await startFight(payload);
      const date = new Date(result.date);
      const formattedDate = new Intl.DateTimeFormat('fr-FR', {
        dateStyle: 'long',
        timeStyle: 'short'
      }).format(date);
      
      // Démarrer l'animation de combat
      startBattleAnimation();
      
      // Afficher le résultat après l'animation
      setTimeout(() => {
        resultEl.innerHTML = `
          <div class="fight-result">
            <div class="battle-time">${formattedDate}</div>
            
            <div class="pokemon-battle">
              ${PokemonCard({ 
                pokemon: result.pokemon1, 
                isWinner: result.winner.id === result.pokemon1.id 
              })}

              <div class="versus">VS</div>

              ${PokemonCard({ 
                pokemon: result.pokemon2, 
                isWinner: result.winner.id === result.pokemon2.id 
              })}
            </div>

            <div class="winner-announcement">
              ${result.winner.name} remporte le combat !
            </div>
          </div>
        `;
      }, 2000); // Attendre la fin de l'animation
    } catch (err) {
      resultEl.textContent = `Erreur lors du combat: ${err.message}`;
      console.error('Error during fight:', err);
    }
  });

  // initial load
  loadFighters();
}