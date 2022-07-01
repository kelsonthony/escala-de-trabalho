package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Mes;

import java.util.List;

public interface IMesService {

  Mes salvar(Mes mes);
  List<Mes> listar();
  Mes listarPorId(long id);
  void atualizar(Mes mes);
  void excluir(long id);

}
