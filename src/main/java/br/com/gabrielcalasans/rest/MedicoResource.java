package br.com.gabrielcalasans.rest;

import br.com.gabrielcalasans.persistence.dto.DadosAtualizarMedicoDTO;
import br.com.gabrielcalasans.persistence.dto.DadosMedicoDTO;
import br.com.gabrielcalasans.persistence.dto.DadosListarMedicoFiltradaDTO;
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
    public Response cadastrarMedido(@Valid DadosMedicoDTO dadosMedicoDTO) {
        Medico medico = medicoService.cadastrarMedico(dadosMedicoDTO);
        return Response
                .status(Response.Status.CREATED)
                .entity(medico)
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMedicos(@QueryParam("page") @DefaultValue("0") Integer page,
                                  @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<DadosListarMedicoFiltradaDTO> medicos = medicoService.listarMedicosFiltrado(page, pageSize);
        return Response.status(Response.Status.OK)
                .entity(medicos)
                .build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarMedico(@Valid DadosAtualizarMedicoDTO medicoDTO) {
        var medico = medicoService.atualizarMedico(medicoDTO);
        return Response
                .status(Response.Status.OK)
                .entity(medico)
                .build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deletarMedico(@PathParam("id") Long id) {
        medicoService.deletarMedico(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }
}
