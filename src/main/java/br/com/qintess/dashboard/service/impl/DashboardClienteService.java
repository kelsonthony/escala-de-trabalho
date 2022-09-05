package br.com.qintess.dashboard.service.impl;

import br.com.qintess.dashboard.entities.Dashboard;
import br.com.qintess.dashboard.repositories.IDashboardRepository;
import br.com.qintess.dashboard.service.IDashboardClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardClienteService implements IDashboardClienteService {

  private final IDashboardRepository dashboardRepository;

  public DashboardClienteService(IDashboardRepository dashboardRepository) {
    this.dashboardRepository = dashboardRepository;
  }

  @Override
  public Dashboard salvar( final Dashboard dashboard) {
    return this.dashboardRepository.save(dashboard);
  }

  @Override
  public List<Dashboard> listar() {
    return this.dashboardRepository.findAll();
  }

  @Override
  public Dashboard listarPorId(final Long id) {
    return this.dashboardRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado pelo ID:" + id));
  }

  @Override
  public Dashboard atualizar( final Dashboard dashboard) {
    return this.dashboardRepository.save(dashboard);
  }

  @Override
  public void excluir(final Long id) {
    this.dashboardRepository.deleteById(id);
  }
}
