package br.com.gabrielcalasans.service;

import br.com.gabrielcalasans.exception.CRMCadastradoException;
import br.com.gabrielcalasans.exception.MedicoIdInvalidoException;
import br.com.gabrielcalasans.persistence.dto.DadosAtualizarMedicoDTO;
import br.com.gabrielcalasans.persistence.dto.DadosListarMedicoFiltradaDTO;
import br.com.gabrielcalasans.persistence.dto.DadosMedicoDTO;
import br.com.gabrielcalasans.persistence.models.Medico;
import br.com.gabrielcalasans.persistence.repository.MedicoRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MedicoService {
    
    @Inject
    private final MedicoRepository medicoRepository;
    
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }
    
    @Transactional
    public Medico cadastrarMedico(DadosMedicoDTO dadosMedicoDTO) {
        Optional<Medico> medicoOptional = medicoRepository.buscarPorCRM(dadosMedicoDTO.crm());
        
        if (medicoOptional.isPresent() && medicoOptional.get().getAtivo()) {
            throw new CRMCadastradoException("CRM já cadastrado!");
        } else if (medicoOptional.isPresent() && !medicoOptional.get().getAtivo()) {
            var medico = medicoRepository.findById(medicoOptional.get().getId());
            medico.setAtivo(true);
            return medico;
        } else {
            var medico = new Medico(dadosMedicoDTO);
            medicoRepository.persist(medico);
            return medico;
        }
    }
    
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll()
                .stream()
                .toList();
    }
    
    public List<DadosListarMedicoFiltradaDTO> listarMedicosFiltrado(Integer page, Integer pageSize) {
        return medicoRepository.findAllByAtivo(Sort.by("nome"))
                .page(Page.of(page, pageSize))
                .stream()
                .map(DadosListarMedicoFiltradaDTO::new)
                .toList();
    }
    
    @Transactional
    public DadosListarMedicoFiltradaDTO atualizarMedico(DadosAtualizarMedicoDTO dados) {
        
        Medico medico = medicoRepository.findById(dados.id());
        atualizarDados(medico, dados);
        return new DadosListarMedicoFiltradaDTO(medico);
    }
    
    private void atualizarDados(Medico medico, DadosAtualizarMedicoDTO dados) {
        
        if (dados.nome() != null) {
            medico.setNome(dados.nome());
        }
        if (dados.telefone() != null) {
            medico.setTelefone(dados.telefone());
        }
        if (dados.dadosEnderecoDTO() != null) {
            medico.getEndereco().atualizarDados(dados.dadosEnderecoDTO());
        }
        
    }
    
    @Transactional
    public void deletarMedico(Long id) {
        Medico medico = medicoRepository.findById(id);
        if (!medico.getAtivo()) {
            throw new MedicoIdInvalidoException("Id não cadastrado ou medico já inativo");
        }
        excluirMedico(medico);
    }
    
    private void excluirMedico(Medico medico) {
        medico.setAtivo(false);
    }
}
