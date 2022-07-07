package br.com.qintess.repositories;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.entities.Mes;
import br.com.qintess.repositories.interfaces.IMesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MesRepository implements IMesRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Mes salvar(final Mes mes) {
    this.em.persist(mes);
    return mes;
  }

  @Override
  public List<Mes> listar() {
    return em.createQuery("SELECT m FROM Mes m", Mes.class).getResultList();
  }

  @Override
  public Mes listarPorId(final long id) {
    return this.em.find(Mes.class,id);
  }

  @Override
  public List<Mes> listarPorIdFuncionarioEDataInicio(long idFuncionario, LocalDate dateInicio) {
    return this.em.createQuery("SELECT m FROM Mes m WHERE m.dataInicio = :dataInicio AND m.funcionario.funcionarioId = :id",Mes.class)
                              .setParameter("dataInicio",dateInicio)
                              .setParameter("id",idFuncionario)
                              .getResultList();
  }

  @Override
  public boolean existeMesPorIdFuncionarioIdEscalaEData(long idFuncionario, long idEscala, LocalDate dataInicio) {

    String query = "SELECT COUNT(m) FROM Mes m WHERE m.funcionario.funcionarioId = :idFuncionario AND m.escala.escalaId = :idEscala AND m.dataInicio = :dataInicio";

    long contador = (long) this.em.createQuery(query).setParameter("idFuncionario",idFuncionario)
                                                   .setParameter("idEscala",idEscala)
                                                   .setParameter("dataInicio",dataInicio)
                                                   .getSingleResult();


    return (contador == 1) ? true : false;

  }

  @Override
  public List<Mes> listarPorData(LocalDate dataInicio) {
    return this.em.createQuery("SELECT m FROM Mes m WHERE m.dataInicio = :dataInicio",Mes.class)
            .setParameter("dataInicio",dataInicio)
            .getResultList();
  }

  @Override
  public List<Mes> listarPorEscala(final long id) {
    return em.createQuery("SELECT m FROM Mes m WHERE m.escala.escalaId = :id", Mes.class)
            .setParameter("id", id)
            .getResultList();
  }

  @Override
  public List<Mes> listarPorEscalaEData(long idEscala, String data) {
    return em.createQuery("SELECT m FROM Mes m WHERE m.dataInicio = :data AND m.escala = :idEscala",Mes.class)
      .setParameter("data",data)
      .setParameter("idEscala",idEscala)
      .getResultList();
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
