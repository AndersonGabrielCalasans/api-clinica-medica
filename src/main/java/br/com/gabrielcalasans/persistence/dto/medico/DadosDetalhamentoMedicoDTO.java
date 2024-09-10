package br.com.gabrielcalasans.persistence.dto.medico;

import br.com.gabrielcalasans.persistence.models.Endereco;
import br.com.gabrielcalasans.persistence.models.Especialidade;
import br.com.gabrielcalasans.persistence.models.Medico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosDetalhamentoMedicoDTO(
        Long id,
        
        @NotBlank(message = "Nome não pode estar em branco")
        String nome,
        
        @NotBlank(message = "Email não pode estar em branco")
        @Email(message = "Formato de email invalido")
        String email,
        
        @NotBlank(message = "crm não pode estar em branco")
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        
        @NotNull(message = "Especialidade não pode ser nulo")
        Especialidade especialidade,
        
        String telefone,
        
        Endereco endereco
) {
    public DadosDetalhamentoMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getTelefone(), medico.getEndereco());
    }
}
