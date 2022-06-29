package br.com.qintess.repositories;

import br.com.qintess.entities.Mes;
import br.com.qintess.repositories.interfaces.IMesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MesRepository implements IMesRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public void salvar(final Mes mes) {
    this.em.persist(mes);
  }

  @Override
  public List<Mes> listar() {
    return em.createQuery("SELECT m FROM TB_MES m",Mes.class).getResultList();
  }

  @Override
  public Mes listarPorId(final long id) {
    return this.em.find(Mes.class,id);
  }

  @Override
  public void atualizar(final Mes mes) {
    this.em.merge(mes);
  }

  @Override
  public void excluir(final long id) {
    this.em.remove(em.getReference(Mes.class,id));
  }
}
