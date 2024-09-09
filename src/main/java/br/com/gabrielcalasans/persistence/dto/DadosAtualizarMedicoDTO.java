package br.com.gabrielcalasans.persistence.dto;

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
