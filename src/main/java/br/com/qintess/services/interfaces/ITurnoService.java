package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Turno;

import java.util.List;

public interface ITurnoService {

  void salvar(Turno turno);
  List<Turno> listar();
  Turno listarPorId(long id);
  List<Turno> listarPorPadraoSistema();
  void atualizar(Turno turno);
  void excluir(long id);

}
