package br.com.gabrielcalasans.persistence.repo;

import br.com.gabrielcalasans.persistence.models.Paciente;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PacienteRepository implements PanacheRepositoryBase<Paciente, Long> {
    
    public PanacheQuery<Paciente> findAllByAtivo(Sort nome) {
        return find("ativo", nome, true);
    }
    
    public Optional<Paciente> buscarPorCPF(String cpf) {
        return find("cpf", cpf).firstResultOptional();
    }
}
