package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Mes;

import java.time.LocalDate;
import java.util.List;

public interface IMesRepository {

  Mes salvar(Mes mes);
  List<Mes> listar();
  Mes listarPorId(long id);
  List<Mes> listarPorIdFuncionarioEDataInicio(long idFuncionario, LocalDate dateInicio);
  List<Mes> listarPorData(LocalDate dataInicio);
  List<Mes> listarPorEscalaData(Escala escala, LocalDate data);
  void atualizar(Mes mes);
  void excluir(long id);

}
