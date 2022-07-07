package br.com.qintess.repositories;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.EscalaTipo;
import br.com.qintess.repositories.interfaces.IEscalaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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
    public LocalDate listarUltimaEscala(){
        return em.createQuery("SELECT max(e.data) FROM Escala e", LocalDate.class).getSingleResult();
    }

    @Override
    public Escala listarPorId(final long id) {
        return em.find(Escala.class, id);
    }

    @Override
    public void atualizar(Escala escala) {
        em.merge(escala);
    }

    @Override
    public void excluir(final long id) {
        em.remove(em.getReference(Escala.class, id));
    }

    @Override
    public void excluirTipo(final long id) {
        em.remove(em.getReference(EscalaTipo.class, id));
    }
}
