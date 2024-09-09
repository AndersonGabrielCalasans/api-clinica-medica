package br.com.gabrielcalasans.persistence.dto;

import br.com.gabrielcalasans.persistence.models.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoDTO(
        
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
