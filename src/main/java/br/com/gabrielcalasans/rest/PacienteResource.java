package br.com.gabrielcalasans.rest;

import br.com.gabrielcalasans.persistence.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.gabrielcalasans.persistence.dto.paciente.DadosCadastroPacienteDTO;
import br.com.gabrielcalasans.persistence.dto.paciente.DadosListagemPacienteDTO;
import br.com.gabrielcalasans.persistence.models.Paciente;
import br.com.gabrielcalasans.services.PacienteService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/pacientes")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class PacienteResource {
    
    private final PacienteService service;
    
    public PacienteResource(PacienteService service) {
        this.service = service;
    }
    
    @POST
    public Response cadastrar(@Valid DadosCadastroPacienteDTO dados) {
        Paciente paciente = service.cadastrar(dados);
        return Response
                .status(Response.Status.CREATED)
                .entity(paciente)
                .build();
    }
    
    @GET
    public Response listar(@QueryParam("page") @DefaultValue("0") Integer page,
                           @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<DadosListagemPacienteDTO> pacientes = service.listar(page, pageSize);
        return Response.status(Response.Status.OK)
                .entity(pacientes)
                .build();
    }
    
    @PUT
    public Response atualizar(@Valid DadosAtualizacaoPacienteDTO dados) {
        var paciente = service.atualizar(dados);
        return Response
                .status(Response.Status.OK)
                .entity(paciente)
                .build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response detalhar(@PathParam("id") Long id) {
        var paciente = service.detalhar(id);
        
        return Response.ok(paciente).build();
    }
}
