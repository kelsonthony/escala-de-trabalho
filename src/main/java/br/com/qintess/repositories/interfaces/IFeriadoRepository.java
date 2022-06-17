package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Feriado;


import java.util.List;


public interface IFeriadoRepository {

  void salvar(Feriado feriado);
  List<Feriado> listar();
  Feriado listarPorId(long id);
  void atualizar(Feriado feriado);
  void excluir(long id);

}
