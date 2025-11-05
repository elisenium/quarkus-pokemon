package org.heg.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.heg.model.exception.PokemonNotFoundException;
import org.heg.model.pokemon.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PokemonServiceTest {

  @Inject
  PokemonService pokemonService;

  @Test
  void shouldFindById() throws PokemonNotFoundException {
    Pokemon pokemon = pokemonService.findById(1L);
    Assertions.assertNotNull(pokemon);
    Assertions.assertEquals(1L, pokemon.getId());
    Assertions.assertEquals("VOLTALI", pokemon.getName());
  }

  @Test
  void shouldListAllPokemon()  {
    List<Pokemon> pokemons = pokemonService.listPokemon();
    Assertions.assertFalse(pokemons.isEmpty());
    Assertions.assertEquals(12, pokemons.size());
  }

  @Test
  void shouldCountPokemon() {
    Long count = pokemonService.countPokemon();
    Assertions.assertEquals(12, count);
  }

  @Test
  void shouldGiveFighters() {
    List<Pokemon> fighters = new ArrayList<>(pokemonService.fighters());
    Assertions.assertFalse(fighters.isEmpty());
    Assertions.assertEquals(2, pokemonService.fighters().stream().distinct().count());
  }

  @Test
  void shouldSearchPokemon() {
    List<Pokemon> pokemons = pokemonService.search("VOLTALI");
    Assertions.assertFalse(pokemons.isEmpty());
    Assertions.assertEquals(1, pokemons.size());
    Pokemon pokemon = pokemons.get(0);
    Assertions.assertNotNull(pokemon);
  }
}