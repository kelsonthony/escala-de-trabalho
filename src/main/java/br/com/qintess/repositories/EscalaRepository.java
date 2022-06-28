package br.com.qintess.repositories;

import br.com.qintess.entities.Escala;
import br.com.qintess.repositories.interfaces.IEscalaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EscalaRepository implements IEscalaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Escala escala) {
        em.persist(escala);
    }

    @Override
    public List<Escala> listar() {
        return em.createQuery("SELECT e FROM Escala e", Escala.class).getResultList();
    }

    @Override
    public Escala listarPorId(long id) {
        return em.find(Escala.class, id);
    }

    @Override
    public void atualizar(Escala escala) {
        em.merge(escala);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Escala.class, id));
    }
}
