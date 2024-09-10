package br.com.gabrielcalasans.rest;

import br.com.gabrielcalasans.persistence.dto.medico.DadosAtualizarMedicoDTO;
import br.com.gabrielcalasans.persistence.dto.medico.DadosMedicoDTO;
import br.com.gabrielcalasans.persistence.dto.medico.DadosListarMedicoFiltradaDTO;
import br.com.gabrielcalasans.services.MedicoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;

@Path("/api/medicos")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class MedicoResource {
    
    @Inject
    private final MedicoService service;
    
    public MedicoResource(MedicoService service) {
        this.service = service;
    }
    
    @POST
    public Response cadastrar(@Valid DadosMedicoDTO dadosMedicoDTO) {
        var medico = service.cadastrarMedico(dadosMedicoDTO);
        URI uri = getUri(medico);
     
        return Response.created(uri).entity(medico).build();
    }
    
    private static URI getUri(@NotNull DadosListarMedicoFiltradaDTO dados) {
        URI uri = UriBuilder.fromResource(MedicoResource.class).path(String.valueOf(dados.id())).build();
     
        return uri;
    }
    
    @GET
    public Response listar(@QueryParam("page") @DefaultValue("0") Integer page,
                           @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<DadosListarMedicoFiltradaDTO> medicos = service.listarMedicosFiltrado(page, pageSize);
     
        return Response.ok().entity(medicos).build();
    }
    
    @PUT
    public Response atualizar(@Valid DadosAtualizarMedicoDTO medicoDTO) {
        DadosListarMedicoFiltradaDTO medico = service.atualizarMedico(medicoDTO);
     
        return Response.ok().entity(medico).build();
    }
    
    @Path("/{id}")
    @DELETE
    public Response deletar(@PathParam("id") Long id) {
        service.deletarMedico(id);
     
        return Response.noContent().build();
    }
    
    @GET
    @Path("/{id}")
    public Response detalhar(@PathParam("id") Long id) {
        var medico = service.detalhar(id);
        
        return Response.ok(medico).build();
    }
}
