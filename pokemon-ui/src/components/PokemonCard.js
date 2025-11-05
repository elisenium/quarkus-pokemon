export function PokemonCard({ pokemon, isWinner }) {
  return `
    <div class="pokemon-card ${isWinner ? 'winner' : 'loser'}" data-type="${pokemon.pokemonType.toLowerCase()}">
      <img src="${pokemon.imageUrl}" alt="${pokemon.name}" />
      <h3>${pokemon.name}</h3>
      <div class="stats">
        <div class="type">${pokemon.pokemonType}</div>
        <div class="hp ${pokemon.lifePoints >= 0 ? 'positive' : 'negative'}">
          ${pokemon.lifePoints} PV
        </div>
      </div>
      <div class="attacks">
        ${pokemon.attacks.map(attack => `
          <div class="attack">
            <span>${attack.name}</span>
            <span class="attack-points">${attack.point}</span>
          </div>
        `).join('')}
      </div>
    </div>
  `;
}