package br.com.qintess.repositories;

import br.com.qintess.entities.Suporte;
import br.com.qintess.repositories.interfaces.ISuporteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SuporteRepository implements ISuporteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(final Suporte suporte) {
        this.em.persist(suporte);
    }

    @Override
    public List<Suporte> listar() {
        return this.em.createQuery("SELECT s FROM Suporte s", Suporte.class).getResultList();
    }
}
