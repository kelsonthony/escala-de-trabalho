package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Mes;
import java.util.List;

public interface IMesRepository {

  Mes salvar(Mes mes);
  List<Mes> listar();
  Mes listarPorId(long id);
  void atualizar(Mes mes);
  void excluir(long id);

}
