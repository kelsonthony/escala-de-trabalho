package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Dia;

import java.util.List;

public interface IDiaService {

  Dia salvar(Dia dia);
  List<Dia> listar();
  Dia listarPorId(long id);
  List<Dia> listarPorMes(Integer mesId);
  void atualizar(Dia dia);
  void excluir(long id);
}
