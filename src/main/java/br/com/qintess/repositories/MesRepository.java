package br.com.qintess.repositories;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Mes;
import br.com.qintess.repositories.interfaces.IMesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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
  public List<Mes> listarPorData(LocalDate dataInicio) {
    return this.em.createQuery("SELECT m FROM Mes m WHERE m.dataInicio = :dataInicio",Mes.class)
            .setParameter("dataInicio",dataInicio)
            .getResultList();
  }

  @Override
  public List<Mes> listarPorEscalaData(Escala escala, LocalDate data) {
    return em.createQuery("SELECT m FROM Mes m WHERE m.dataInicio = :data AND m.escala = :escalaId",Mes.class)
      .setParameter("data",data)
      .setParameter("escalaId",escala.getEscalaId())
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
