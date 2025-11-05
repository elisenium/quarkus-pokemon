package org.heg.model.exception;

public class PokemonNotFoundException extends PokemonApplicationException {

    public PokemonNotFoundException(String message) {
        super(message);
    }
}
