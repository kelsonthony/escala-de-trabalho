package br.com.qintess.dashboard.service;

import br.com.qintess.dashboard.entities.Especialidade;
import java.util.List;

public interface IEspecialidadeService {

  Especialidade salvar(Especialidade especialidade);
  List<Especialidade> listar();
  Especialidade listarPorId(Long id);
  Especialidade atualizar(Especialidade especialidade);
  void excluir(Long id);
}
