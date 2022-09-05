package br.com.qintess.dashboard.service.impl;

import br.com.qintess.dashboard.entities.Especialidade;
import br.com.qintess.dashboard.repositories.IEspecialidadeRepository;
import br.com.qintess.dashboard.service.IEspecialidadeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService implements IEspecialidadeService {

  private final IEspecialidadeRepository especialidadeRepository;

  public EspecialidadeService(IEspecialidadeRepository especialidadeRepository) {
    this.especialidadeRepository = especialidadeRepository;
  }

  @Override
  public Especialidade salvar(final Especialidade especialidade) {
    return this.especialidadeRepository.save(especialidade);
  }

  @Override
  public List<Especialidade> listar() {
    return this.especialidadeRepository.findAll();
  }

  @Override
  public Especialidade listarPorId(final Long id) {
    return this.especialidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado pelo ID:" + id));
  }

  @Override
  public Especialidade atualizar(final Especialidade especialidade) {
    return this.especialidadeRepository.save(especialidade);
  }

  @Override
  public void excluir(final Long id) {
     this.especialidadeRepository.deleteById(id);
  }
}
