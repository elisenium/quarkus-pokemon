package org.heg.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.heg.model.pokemon.Pokemon;

import java.util.List;

@Path("/pokemons")
@RegisterClientHeaders
@RegisterRestClient(configKey = "rest-pokemon")
public interface IPokemonAPI {

    @GET
    @Path("/show")
    List<Pokemon> fighters();

    @GET
    @Path("/pokemon/{id}")
    Pokemon findPokemon(@PathParam("id") Long id);
}
