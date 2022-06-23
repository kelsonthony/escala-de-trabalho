package br.com.qintess.services.interfaces;


import br.com.qintess.entities.Log;
import java.util.List;

public interface ILogService {

  void salvar(Log log);
  List<Log> listar();

}
