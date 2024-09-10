package br.com.gabrielcalasans.persistence.models;

import br.com.gabrielcalasans.persistence.dto.endereco.DadosEnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Endereco {
    
    private String logradouro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String bairro;
    private String complemento;
    
    public Endereco(DadosEnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.cep = enderecoDTO.cep();
        this.cidade = enderecoDTO.cidade();
        this.uf = enderecoDTO.uf();
        this.numero = enderecoDTO.numero();
        this.bairro = enderecoDTO.bairro();
        this.complemento = enderecoDTO.complemento();
    }
    
    public void atualizarDados(DadosEnderecoDTO dados) {
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
    }
}

