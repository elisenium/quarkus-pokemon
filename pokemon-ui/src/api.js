// En utilisant le proxy Vite, on utilise des chemins relatifs
// Le proxy redirigera /fighters vers http://localhost:8030
// et /pokemons vers http://localhost:8010

export async function getFighters() {
  console.debug('[API] Fetching fighters from:', `/fighters/show`);
  const res = await fetch(`/fighters/show`); // Nouveau endpoint pour afficher les combattants
  if (!res.ok) {
    const errorText = await res.text().catch(() => 'No error details');
    throw new Error(`HTTP ${res.status} - ${errorText}`);
  }
  return res.json();
}

export async function startFight(payload) {
  if (!payload?.fighter1?.id || !payload?.fighter2?.id) {
    throw new Error('Les IDs des deux Pokémon sont requis');
  }
  console.debug('[API] Starting fight between pokemons:', payload.fighter1.id, 'and', payload.fighter2.id);
  const res = await fetch(`/fighters/fight?pokemonId1=${payload.fighter1.id}&pokemonId2=${payload.fighter2.id}`);
  if (!res.ok) {
    const errorText = await res.text().catch(() => 'No error details');
    throw new Error(`HTTP ${res.status} - ${errorText}`);
  }
  return res.json();
}

export async function listPokemons() {
  const res = await fetch(`/pokemons/list`); // Endpoint pour lister les Pokémons
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.json();
}

export async function showPokemon(id) {
  const res = await fetch(`/pokemons/pokemon/${id}`); // Endpoint pour afficher un Pokémon par ID
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.json();
}
