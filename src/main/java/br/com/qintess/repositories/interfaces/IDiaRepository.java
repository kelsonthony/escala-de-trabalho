package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Dia;

import java.time.LocalDate;
import java.util.List;

public interface IDiaRepository {

  Dia salvar(Dia dia);
  List<Dia> listar();
  Dia listarPorId(long id);
  List<Dia> listarPorMes(Integer mes);
  void atualizar(Dia dia);
  void excluir(long id);

}
