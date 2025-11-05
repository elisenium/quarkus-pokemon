package org.heg.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.heg.model.exception.ErrorMessage;
import org.heg.model.exception.PokemonApplicationException;
import org.heg.model.exception.PokemonNotFoundException;

@Provider
public class PokemonApplicationExceptionHandler implements ExceptionMapper<PokemonApplicationException> {

    @Override
    public Response toResponse(PokemonApplicationException e) {
        if (e instanceof PokemonNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ErrorMessage(Response.Status.NOT_FOUND.getStatusCode(), "https://heg.net/validation-error", "PokemonNotFound", e.getMessage())).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(new ErrorMessage(Response.Status.BAD_REQUEST.getStatusCode(),"https://heg.net/validation-error", "Undetermined exception", e.getMessage())).build();
        }
    }
}
