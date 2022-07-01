package br.com.qintess.services.interfaces;


import br.com.qintess.entities.Mes;

import java.util.List;

public interface IGerenciamentoMesFixoService {

  void cadastrarMes(Mes mes);
  void cadastrarListaMeses(List<Mes> meses);

}
