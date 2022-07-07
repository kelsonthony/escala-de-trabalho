package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;

public interface IGerenciamentoMesService {

  void gerarMesPorFuncionario(Funcionario funcionario, Escala escala);

}
