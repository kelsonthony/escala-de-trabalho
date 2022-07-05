package br.com.qintess.repositories;

import br.com.qintess.entities.Dia;
import br.com.qintess.repositories.interfaces.IDiaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DiaRepository implements IDiaRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Dia salvar(final Dia dia) {
    this.em.persist(dia);
    return dia;
  }

  @Override
  public List<Dia> listar() {
    return em.createQuery("select d from Dia d", Dia.class).getResultList();
  }

  @Override
  public Dia listarPorId(final long id) {
    return this.em.find(Dia.class,id);
  }

  @Override
  public List<Dia> listarPorMes(Integer mesId) {
    return this.em.createQuery("SELECT d FROM Dia d WHERE d.mesId = :mesId",Dia.class)
            .setParameter("mesId",mesId)
            .getResultList();
  }

  @Override
  public void atualizar(final Dia dia) {
    this.em.merge(dia);
  }

  @Override
  public void excluir(final long id) {
    this.em.remove(em.getReference(Dia.class,id));
  }
}
