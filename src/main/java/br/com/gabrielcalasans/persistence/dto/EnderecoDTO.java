package br.com.gabrielcalasans.persistence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        
        @NotBlank(message = "Logradouro não pode estar em branco")
        String logradouro,
        
        @NotBlank(message = "CEP não pode estar em branco")
        @Pattern(regexp = "\\d{8}")
        String cep,
        
        @NotBlank(message = "Cidade não pode estar em branco")
        String cidade,
        
        @NotBlank(message = "UF não pode estar em branco")
        String uf,
        
        @NotBlank(message = "Bairro não pode estar em branco")
        String bairro,
        
        String numero,
        
        String complemento
) {
}
