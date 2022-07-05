package br.com.qintess.services;

import br.com.qintess.entities.Dia;
import br.com.qintess.repositories.interfaces.IDiaRepository;
import br.com.qintess.services.interfaces.IDiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiaService implements IDiaService {

  @Autowired
  private IDiaRepository diaRepository;


  @Override
  public Dia salvar(Dia dia) {
    return this.diaRepository.salvar(dia);
  }

  @Override
  public List<Dia> listar() {
    return this.diaRepository.listar();
  }

  @Override
  public Dia listarPorId(long id) {
    return this.diaRepository.listarPorId(id);
  }

  @Override
  public List<Dia> listarPorMes(Integer mesId) {
    return this.diaRepository.listarPorMes(mesId);
  }

  @Override
  public void atualizar(final Dia dia) {
    this.diaRepository.atualizar(dia);
  }

  @Override
  public void excluir(final long id) {
    this.diaRepository.excluir(id);
  }
}



