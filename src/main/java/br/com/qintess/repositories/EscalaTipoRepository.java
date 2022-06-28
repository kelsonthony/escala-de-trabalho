package br.com.qintess.repositories;

import br.com.qintess.entities.EscalaTipo;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.repositories.interfaces.IEscalaTipoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EscalaTipoRepository implements IEscalaTipoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(EscalaTipo escalaTipo) {
        em.persist(escalaTipo);
    }

    @Override
    public List<EscalaTipo> listar() {
        return em.createQuery("SELECT et FROM EscalaTipo et", EscalaTipo.class).getResultList();
    }

    @Override
    public EscalaTipo listarPorId(long id) {
        return em.find(EscalaTipo.class, id);
    }

    @Override
    public List<EscalaTipo> listarPorEscala(long escalaId) {
        return em.createQuery("SELECT et FROM EscalaTipo et WHERE et.escala.escalaId = :escalaId", EscalaTipo.class)
                .setParameter("escalaId", escalaId)
                .getResultList();
    }

    @Override
    public void atualizar(EscalaTipo escalaTipo) {
        em.merge(escalaTipo);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(EscalaTipo.class, id));
    }
}
