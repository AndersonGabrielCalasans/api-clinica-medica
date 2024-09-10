package br.com.gabrielcalasans.persistence.models;

import br.com.gabrielcalasans.persistence.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.gabrielcalasans.persistence.dto.paciente.DadosCadastroPacienteDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    
    private String telefone;
    
    private String cpf;
    
    @Embedded
    private Endereco endereco;
    
    private Boolean ativo;
    
    public Paciente(DadosCadastroPacienteDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }
    
    public void atualizarInformacoes(DadosAtualizacaoPacienteDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarDados(dados.endereco());
        }
        
    }
    
    public void excluir() {
        this.ativo = false;
    }
}
