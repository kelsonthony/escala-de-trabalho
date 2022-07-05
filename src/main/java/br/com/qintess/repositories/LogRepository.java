package br.com.qintess.repositories;

import br.com.qintess.entities.Log;
import br.com.qintess.repositories.interfaces.ILogRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LogRepository implements ILogRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public void salvar(final Log log) {
     this.em.persist(log);
  }

  @Override
  public List<Log> listar() {
    return this.em.createQuery("SELECT l FROM Log l", Log.class).getResultList();
  }

}
