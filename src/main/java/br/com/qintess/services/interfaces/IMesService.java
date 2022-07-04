package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Mes;

import java.time.LocalDate;
import java.util.List;

public interface IMesService {

  Mes salvar(Mes mes);
  List<Mes> listar();
  Mes listarPorId(long id);
  Mes listarPorIdFuncionarioEDataInicio(long idFuncionario, LocalDate dateInicio);
  List<Mes> listarPorData(LocalDate dataInicio);
  void atualizar(Mes mes);
  void excluir(long id);
  List<LocalDate> dias(LocalDate data);
  int totalDiasDoMes(int ano, int mes);
}
