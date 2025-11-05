package org.heg.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import org.heg.model.PokemonStatistics;
import org.heg.model.exception.PokemonNotFoundException;
import org.heg.model.pokemon.Pokemon;
import org.heg.service.PokemonService;

@Path("/pokemons")
public class PokemonController {

  @Inject
  PokemonService pokemonService;

  @GET
  @Path("/pokemon/{id}")
  public Pokemon findPokemon(@PathParam("id") Long id) throws PokemonNotFoundException {
    return pokemonService.findById(id);
  }

  @GET
  @Path("/list")
  public List<Pokemon> listPokemon() {
    return pokemonService.listPokemon();
  }

  @GET
  @Path("/show")
  public List<Pokemon> fighters() {
    return pokemonService.fighters();
  }

  @GET
  @Path("/count")
  public PokemonStatistics countPokemon() {
    return new PokemonStatistics(pokemonService.countPokemon());
  }

  @GET
  @Path("/search")
  public List<Pokemon> searchPokemon(@QueryParam("name") String name) {
    return pokemonService.search(name);
  }
}
