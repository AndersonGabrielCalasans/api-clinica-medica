package br.com.gabrielcalasans.services;

import br.com.gabrielcalasans.exception.CRMCadastradoException;
import br.com.gabrielcalasans.exception.MedicoIdInvalidoException;
import br.com.gabrielcalasans.exception.MedicoNotFoundException;
import br.com.gabrielcalasans.persistence.dto.medico.DadosAtualizarMedicoDTO;
import br.com.gabrielcalasans.persistence.dto.medico.DadosDetalhamentoMedicoDTO;
import br.com.gabrielcalasans.persistence.dto.medico.DadosListarMedicoFiltradaDTO;
import br.com.gabrielcalasans.persistence.dto.medico.DadosMedicoDTO;
import br.com.gabrielcalasans.persistence.models.Medico;
import br.com.gabrielcalasans.persistence.repo.MedicoRepository;
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
    private final MedicoRepository repository;
    
    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }
    
    @Transactional
    public DadosListarMedicoFiltradaDTO cadastrarMedico(DadosMedicoDTO dadosMedicoDTO) {
        Optional<Medico> medicoOptional = repository.buscarPorCRM(dadosMedicoDTO.crm());
        
        if (medicoOptional.isPresent() && medicoOptional.get().getAtivo()) {
            throw new CRMCadastradoException("CRM já cadastrado!");
        } else if (medicoOptional.isPresent() && !medicoOptional.get().getAtivo()) {
            var medico = repository.findById(medicoOptional.get().getId());
            medico.setAtivo(true);
            return new DadosListarMedicoFiltradaDTO(medico);
        } else {
            var medico = new Medico(dadosMedicoDTO);
            repository.persist(medico);
            return new DadosListarMedicoFiltradaDTO(medico);
        }
    }
    
    public List<Medico> listarMedicos() {
        return repository.findAll()
                .stream()
                .toList();
    }
    
    public List<DadosListarMedicoFiltradaDTO> listarMedicosFiltrado(Integer page, Integer pageSize) {
        return repository.findAllByAtivo(Sort.by("nome"))
                .page(Page.of(page, pageSize))
                .stream()
                .map(DadosListarMedicoFiltradaDTO::new)
                .toList();
    }
    
    @Transactional
    public DadosListarMedicoFiltradaDTO atualizarMedico(DadosAtualizarMedicoDTO dados) {
        
        Medico medico = repository.findById(dados.id());
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
        Medico medico = repository.findById(id);
        if (!medico.getAtivo()) {
            throw new MedicoIdInvalidoException("Id não cadastrado ou medico já inativo");
        }
        medico.setAtivo(false);
    }

    public DadosDetalhamentoMedicoDTO detalhar(Long id) {
        var medico = repository.findByIdOptional(id).orElseThrow(() -> new MedicoNotFoundException("Id informado não encontrado"));
        
        return  new DadosDetalhamentoMedicoDTO(medico);
    }
}
