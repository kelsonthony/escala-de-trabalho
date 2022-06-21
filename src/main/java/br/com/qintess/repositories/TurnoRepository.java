package br.com.qintess.repositories;

import br.com.qintess.entities.Turno;
import br.com.qintess.repositories.interfaces.ITurnoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TurnoRepository implements ITurnoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Turno turno) {
        this.em.persist(turno);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Turno> listar() {
        return this.em.createQuery("SELECT t FROM Turno t",Turno.class).getResultList();
    }

    @Override
    public Turno listarPorId(long id) {
        return em.createQuery("SELECT t FROM Turno t WHERE t.id =:id", Turno.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public void atualizar(Turno turno) {
          this.em.merge(turno);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Turno.class,id));
    }

}
