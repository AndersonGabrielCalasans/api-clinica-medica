package br.com.gabrielcalasans.services;

import br.com.gabrielcalasans.exception.BusinessException;
import br.com.gabrielcalasans.persistence.dto.paciente.DadosAtualizacaoPacienteDTO;
import br.com.gabrielcalasans.persistence.dto.paciente.DadosCadastroPacienteDTO;
import br.com.gabrielcalasans.persistence.dto.paciente.DadosDetalhamentoPacienteDTO;
import br.com.gabrielcalasans.persistence.dto.paciente.DadosListagemPacienteDTO;
import br.com.gabrielcalasans.persistence.models.Paciente;
import br.com.gabrielcalasans.persistence.repo.PacienteRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Paciente cadastrar(DadosCadastroPacienteDTO dados) {
        Optional<Paciente> pacienteOptional = repository.buscarPorCPF(dados.cpf());

        if (pacienteOptional.isPresent() && !pacienteOptional.get().getAtivo()) {
            var paciente = repository.findById(pacienteOptional.get().getId());
            paciente.setAtivo(true);
            return paciente;
        } else if (pacienteOptional.isPresent() && pacienteOptional.get().getAtivo()) {
            throw new BusinessException.JaCadastradoException("Erro: Paciente já cadastrado!");
        } else {
            var paciente = new Paciente(dados);
            repository.persist(paciente);
            return paciente;
        }
    }

    public List<DadosListagemPacienteDTO> listar(Integer page, Integer pageSize) {
        return repository.findAllByAtivo(Sort.by("nome"))
                .page(Page.of(page, pageSize))
                .stream()
                .map(DadosListagemPacienteDTO::new)
                .toList();
    }

    @Transactional
    public DadosListagemPacienteDTO atualizar(DadosAtualizacaoPacienteDTO dados) {

        Paciente paciente = repository.findById(dados.id());
        atualizarDados(paciente, dados);
        return new DadosListagemPacienteDTO(paciente);
    }

    private void atualizarDados(Paciente paciente, DadosAtualizacaoPacienteDTO dados) {

        if (dados.nome() != null) {
            paciente.setNome(dados.nome());
        }
        if (dados.telefone() != null) {
            paciente.setTelefone(dados.telefone());
        }
        if (dados.endereco() != null) {
            paciente.getEndereco().atualizarDados(dados.endereco());
        }

    }

    @Transactional
    public void deletar(Long id) {
        Paciente paciente = repository.findById(id);
        if (!paciente.getAtivo()) {
            throw new BusinessException.IdInvalidoException("Erro: Id não cadastrado ou paciente já inativo");
        }
        excluirPacienteLogico(paciente);
    }

    private void excluirPacienteLogico(Paciente paciente) {
        paciente.setAtivo(false);
    }

    public DadosDetalhamentoPacienteDTO detalhar(Long id) {
        var paciente = repository.findByIdOptional(id).orElseThrow(() -> new BusinessException.NotFoundException("Erro: Id informado não encontrado"));

        return new DadosDetalhamentoPacienteDTO(paciente);
    }
}