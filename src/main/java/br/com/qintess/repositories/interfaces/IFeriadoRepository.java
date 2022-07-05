package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Feriado;


import java.time.LocalDate;
import java.util.List;


public interface IFeriadoRepository {

  void salvar(Feriado feriado);
  List<Feriado> listar();
  Feriado listarPorId(long id);
  List<Feriado> listaPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
  List<Feriado> listarPorNomeEData(String nome,LocalDate data);
  void atualizar(Feriado feriado);
  void excluir(long id);

}
