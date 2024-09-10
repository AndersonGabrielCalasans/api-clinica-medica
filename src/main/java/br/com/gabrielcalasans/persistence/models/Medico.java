package br.com.gabrielcalasans.persistence.models;

import br.com.gabrielcalasans.persistence.dto.medico.DadosMedicoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicos", schema = "api_clinica_med")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;
    
    public Medico(DadosMedicoDTO dadosMedicoDTO) {
        this.nome = dadosMedicoDTO.nome();
        this.email = dadosMedicoDTO.email();
        this.telefone = dadosMedicoDTO.telefone();
        this.crm = dadosMedicoDTO.crm();
        this.especialidade = dadosMedicoDTO.especialidade();
        this.endereco = new Endereco(dadosMedicoDTO.endereco());
        this.ativo = true;
    }
    
}
