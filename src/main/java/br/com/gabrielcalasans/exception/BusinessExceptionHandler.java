package br.com.gabrielcalasans.exception;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response toResponse(BusinessException exception) {

        ErrorResponse error = new ErrorResponse(
                exception.getERROR_NAME(),
                exception.getCODE_ERROR(),
                exception.getSTATUS_CODE(),
                exception.getMessage()
                );

        return Response.status(error.getStatusCode())
                .entity(error)
                .build();
    }

}
