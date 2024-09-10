package br.com.gabrielcalasans.persistence.dto.paciente;

import br.com.gabrielcalasans.persistence.models.Endereco;
import br.com.gabrielcalasans.persistence.models.Especialidade;
import br.com.gabrielcalasans.persistence.models.Medico;
import br.com.gabrielcalasans.persistence.models.Paciente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosDetalhamentoPacienteDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco endereco
) {
    public DadosDetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
