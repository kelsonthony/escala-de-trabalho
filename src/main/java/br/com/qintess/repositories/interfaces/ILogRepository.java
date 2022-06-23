package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Log;

import java.util.List;

public interface ILogRepository {

  void salvar(Log log);
  List<Log> listar();

}
