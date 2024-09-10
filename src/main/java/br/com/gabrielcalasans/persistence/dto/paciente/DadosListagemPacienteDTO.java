package br.com.gabrielcalasans.persistence.dto.paciente;

import br.com.gabrielcalasans.persistence.models.Paciente;

public record DadosListagemPacienteDTO (Long id, String nome, String email, String cpf) {

    public DadosListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
    
}