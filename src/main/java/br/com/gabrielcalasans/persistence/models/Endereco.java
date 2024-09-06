package br.com.gabrielcalasans.persistence.models;

import br.com.gabrielcalasans.persistence.dto.EnderecoDTO;
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
    
    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.cep = enderecoDTO.cep();
        this.cidade = enderecoDTO.cidade();
        this.uf = enderecoDTO.uf();
        this.numero = enderecoDTO.numero();
        this.bairro = enderecoDTO.bairro();
        this.complemento = enderecoDTO.complemento();
    }
}

