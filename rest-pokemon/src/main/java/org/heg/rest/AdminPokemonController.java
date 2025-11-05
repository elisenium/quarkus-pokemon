package org.heg.rest;

import io.quarkus.logging.Log;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.heg.model.pokemon.Pokemon;
import org.heg.model.pokemon.PokemonType;
import org.heg.pojo.ResultMessage;

import java.util.Collections;
import java.util.List;
import org.heg.service.PokemonService;

@Path("/admin")
public class AdminPokemonController {

    private final PokemonService pokemonService;

    @Inject
    public AdminPokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @CheckedTemplate
    private static class Templates {
        public static native TemplateInstance pokemons(List<Pokemon> pokemons);

        public static native TemplateInstance home();

        public static native TemplateInstance add(List<PokemonType> pokemonTypes);

        public static native TemplateInstance add_message(ResultMessage resultMessage);

        public static native TemplateInstance count(long count);

    }

//    @Inject
//    PokemonService pokemonService;

    @GET
    @Path("/home")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance home() {
        return Templates.home();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance count() {
        Long l = pokemonService.countPokemon();
        return Templates.count(l);
    }

    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance listPokemon() {
        List<Pokemon> pokemons = pokemonService.listPokemon();
        Log.info("found %s pokemons".formatted(pokemons.size()));
        return Templates.pokemons(pokemons);
    }

    @GET
    @Path("/pokemon")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance search(@QueryParam("search") String searchParam) {
        Log.info("search pokemons with param %s".formatted(searchParam));
        List<Pokemon> pokemons = pokemonService.search(searchParam);
        Log.info("found %s pokemons".formatted(pokemons.size()));
        return Templates.pokemons(pokemons);
    }

//    @GET
//    @Path("/showAddForm")
//    @Produces(MediaType.TEXT_HTML)
//    public TemplateInstance getAddTemplate() {
//        List<PokemonType> pokemonTypes = pokemonService.listType();
//        return Templates.add(pokemonTypes);
//    }
//
//    @POST
//    @Path("/add")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public TemplateInstance addPokemon(@FormParam("pokemonName") String name,
//                           @FormParam("lifePoints") Integer lifePoints,
//                           @FormParam("number") String number,
//                           @FormParam("familly") String familly,
//                           @FormParam("size") String size,
//                           @FormParam("height") String height,
//                           @FormParam("description") String description,
//                           @FormParam("imageUrl") String imageUrl,
//                           @FormParam("pokemonType") String pokemonType) {
//        try {
//            String resume = "N° %s Pokemon %s, Taille: %sm, Poids: %skg".formatted(number, familly, size, height);
//            Pokemon pokemon = new Pokemon(null, name, lifePoints, resume, description, imageUrl, PokemonType.valueOf(pokemonType), Collections.emptyList());
//            pokemonService.addPokemon(pokemon);
//            return Templates.add_message(new ResultMessage(false, "Pokemon successfully added"));
//        } catch (Exception e) {
//            return Templates.add_message(new ResultMessage(true, e.getCause().getCause().getMessage()));
//        }
//    }
}
