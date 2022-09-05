package br.com.qintess.dashboard.service;

import br.com.qintess.dashboard.entities.Dashboard;
import java.util.List;

public interface IDashboardClienteService {

  Dashboard salvar(Dashboard dashboard);
  List<Dashboard> listar();
  Dashboard listarPorId(Long id);
  Dashboard atualizar(Dashboard dashboard);
  void excluir(Long id);

}
