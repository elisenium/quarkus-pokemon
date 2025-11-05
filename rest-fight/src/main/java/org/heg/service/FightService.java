package org.heg.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.heg.model.fight.FightResult;
import org.heg.model.pokemon.Pokemon;
import org.heg.rest.IPokemonAPI;

import java.util.List;

@ApplicationScoped
public class FightService {

    @Inject
    @RestClient
    IPokemonAPI iPokemonAPI;

    public List<Pokemon> getFighters() {
        return iPokemonAPI.fighters();
    }

    /**
     * @param pokemonId1
     * @param pokemonId2
     * @return
     */
    public FightResult fight(Long pokemonId1, Long pokemonId2) {
        Pokemon pokemon1 = iPokemonAPI.findPokemon(pokemonId1);
        Pokemon pokemon2 = iPokemonAPI.findPokemon(pokemonId2);

        while (pokemon1.isAlive() && pokemon2.isAlive()) {
            pokemon1.attack(pokemon2);
            if (pokemon2.isAlive()) {
                pokemon2.attack(pokemon1);
            }
        }
        return new FightResult(pokemon1, pokemon2, new Date());
    }
}
