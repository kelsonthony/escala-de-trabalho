package br.com.qintess.services.interfaces;


import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.entities.Mes;

import java.util.List;

public interface IGerenciamentoMesFixoService {

  void cadastrarMes(Funcionario funcionario, Escala escala);
  void cadastrarListaMeses(List<Mes> meses);

}
