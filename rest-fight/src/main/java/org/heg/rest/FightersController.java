package org.heg.rest;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.heg.model.fight.FightResult;
import org.heg.model.pokemon.Pokemon;
import org.heg.service.FightService;

import java.util.List;

@Path("/fighters")
public class FightersController {

    @Inject
    FightService fightService;

    @GET
    @Path("/fight")
    public FightResult fight(@QueryParam("pokemonId1") Long pokemonId1, @QueryParam("pokemonId2") Long pokemonId2) {
        return fightService.fight(pokemonId1, pokemonId2);
    }

    @GET
    @Path("/show")
    public List<Pokemon> getFighters() {
        return fightService.getFighters();
    }
}
