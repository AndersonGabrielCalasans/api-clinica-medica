package br.com.gabrielcalasans.rest;

import br.com.gabrielcalasans.persistence.dto.MedicoDTO;
import br.com.gabrielcalasans.persistence.models.Medico;
import br.com.gabrielcalasans.service.MedicoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/medicos")
@RequestScoped
public class MedicoResource {
    
    @Inject
    private final MedicoService medicoService;
    
    public MedicoResource(MedicoService medicoService) {
        this.medicoService = medicoService;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarMedido(@Valid MedicoDTO medicoDTO) {
        Medico medico = medicoService.cadastrarMedico(medicoDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity(medico)
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMedicos() {
        List<Medico> medicos = medicoService.listarMedicos();
        return Response.status(Response.Status.OK)
                .entity(medicos)
                .build();
    }
}
