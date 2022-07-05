package br.com.qintess.repositories;

import br.com.qintess.entities.Feriado;
import br.com.qintess.repositories.interfaces.IFeriadoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
public class FeriadoRepository implements IFeriadoRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public void salvar(final Feriado feriado) {
    this.em.persist(feriado);
  }

  @Override
  public List<Feriado> listar() {
    return this.em.createQuery("SELECT f FROM Feriado f",Feriado.class).getResultList();
  }

  @Override
  public Feriado listarPorId(final long id) {
    return this.em.find(Feriado.class,id);
  }

  @Override
  public List<Feriado> listaPorPeriodo(LocalDate dataInicio, LocalDate dataFinal) {

    return this.em.createQuery("SELECT f FROM Feriado f WHERE f.data >= :dataInicio and  f.data <= :dataFinal", Feriado.class)
                              .setParameter("dataInicio",dataInicio)
                              .setParameter("dataFinal",dataFinal)
                              .getResultList();
  }

  @Override
  public List<Feriado> listarPorNomeEData(String nome, LocalDate data) {
    return this.em.createQuery("SELECT f FROM Feriado f WHERE f.nome LIKE :nome AND f.data = :data",Feriado.class)
      .setParameter("nome",nome)
      .setParameter("data",data)
      .getResultList();
  }


  @Override
  public void atualizar(final Feriado feriado) {
    this.em.merge(feriado);
  }

  @Override
  public void excluir(long id) {
    this.em.remove(this.em.getReference(Feriado.class,id));

  }

}
