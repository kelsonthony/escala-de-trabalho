package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Mes;

import java.time.LocalDate;
import java.util.List;

public interface IMesService {

  Mes salvar(Mes mes);
  List<Mes> listar();
  Mes listarPorId(long id);
  List<Mes> listarPorIdFuncionarioEDataInicio(long idFuncionario, LocalDate dateInicio);
  List<Mes> listarPorData(LocalDate dataInicio);
  List<Mes> listarPorEscala(long id);
  void atualizar(Mes mes);
  void excluir(long id);
  List<String> dias(LocalDate data);
  int totalDiasDoMes(int ano, int mes);
}
