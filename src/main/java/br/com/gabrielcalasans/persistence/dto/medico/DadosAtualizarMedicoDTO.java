package br.com.gabrielcalasans.persistence.dto.medico;

import br.com.gabrielcalasans.persistence.dto.endereco.DadosEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedicoDTO(
        @NotNull(message = "Id tem que ser informado")
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEnderecoDTO dadosEnderecoDTO
) {
}
