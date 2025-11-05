package org.heg.service;

import static org.junit.jupiter.api.Assertions.*;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Date;
import org.heg.model.fight.FightResult;
import org.heg.model.pokemon.Pokemon;

@QuarkusTest
class FightServiceTest {

  @Inject
  FightService fightService;

  // test de la méthode "fight" du service
  //@Test
  void shouldHaveFightResult() {
    Pokemon pokemon1 = fightService.iPokemonAPI.findPokemon(1L);
    Pokemon pokemon2 = fightService.iPokemonAPI.findPokemon(2L);

    FightResult fightResult = new FightResult(pokemon1, pokemon2, new Date());
    assertNotNull(fightResult);
  }

  // test de la méthode "getFighters" du service
  //@Test
  void shouldGiveFighters() {
    Pokemon pokemon1 = fightService.iPokemonAPI.findPokemon(1L);
    Pokemon pokemon2 = fightService.iPokemonAPI.findPokemon(2L);

    FightResult fightResult = new FightResult(pokemon1, pokemon2, new Date());
    assertEquals(pokemon1, fightResult.getPokemon1());
    assertEquals(pokemon2, fightResult.getPokemon2());
  }

}