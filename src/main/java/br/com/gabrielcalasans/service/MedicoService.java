package br.com.gabrielcalasans.service;

import br.com.gabrielcalasans.exception.CRMCadastradoException;
import br.com.gabrielcalasans.persistence.dto.MedicoDTO;
import br.com.gabrielcalasans.persistence.dto.MedicoListagemFiltradaDTO;
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
    public Medico cadastrarMedico(MedicoDTO medicoDTO) {
        Optional<Medico> medicoOptional =  medicoRepository.buscarPorCRM(medicoDTO.crm());
        
        if (medicoOptional.isPresent()) {
            throw new CRMCadastradoException("CRM já cadastrado!");
        }
         Medico medico = new Medico(medicoDTO);
         medicoRepository.persist(medico);
         return medico;
    }
    
    public List<Medico> listarMedicos() {
        return medicoRepository.findAll()
                .stream()
                .toList();
    }
    
    public List<MedicoListagemFiltradaDTO> listarMedicosFiltrado(Integer page, Integer pageSize) {
        return medicoRepository.findAll(Sort.by("nome"))
                .page(Page.of(page, pageSize))
                .stream()
                .map(MedicoListagemFiltradaDTO::new)
                .toList();
    }
    
}
