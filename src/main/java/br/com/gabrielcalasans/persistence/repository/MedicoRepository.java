package br.com.gabrielcalasans.persistence.repository;

import br.com.gabrielcalasans.persistence.models.Medico;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class MedicoRepository implements PanacheRepositoryBase<Medico, Long> {

    public Optional<Medico> buscarPorCRM(String crm) {
        return find("crm", crm).firstResultOptional();
    }
    
    public PanacheQuery<Medico> findAllByAtivo(Sort nome) {
        return find("ativo", nome, true);
    }
}
