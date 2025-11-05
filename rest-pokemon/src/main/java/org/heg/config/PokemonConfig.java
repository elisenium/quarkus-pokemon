package org.heg.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "pokemon")
public interface PokemonConfig {

    Life life();

    interface Life {

        @WithDefault("50")
        Integer bonus();

        @WithDefault("false")
        boolean addBonus();
    }
}
