package br.com.gabrielcalasans.persistence.dto.paciente;

import br.com.gabrielcalasans.persistence.dto.endereco.DadosEnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPacienteDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco
) {
}
