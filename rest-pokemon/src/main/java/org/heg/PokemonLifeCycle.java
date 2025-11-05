package org.heg;

import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ConfigUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.heg.config.PokemonConfig;

@ApplicationScoped
public class PokemonLifeCycle {

    @Inject
    PokemonConfig pokemonConfig;

    void onStart(@Observes StartupEvent ev) {
        Log.info("The application REST-POKEMON is starting with profile " + ConfigUtils.getProfiles());
        Log.info("Life bonus is set to " + pokemonConfig.life().addBonus());
    }

    void onStop(@Observes ShutdownEvent ev) {
        Log.info("The application REST-POKEMON is stopping...");
    }
}
