package br.com.qintess.services;

import br.com.qintess.entities.Turno;
import br.com.qintess.repositories.TurnoRepository;
import br.com.qintess.services.interfaces.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

  @Autowired
  private TurnoRepository turnoRepository;

  @Override
  public void salvar(Turno turno) {

  }

  @Override
  public List<Turno> listar() {
    return this.turnoRepository.listar();
  }

  @Override
  public Turno listarPorId(long id) {
    return this.turnoRepository.listarPorId(id);
  }

  @Override
  public void atualizar(Turno turno) {

  }

  @Override
  public void excluir(long id) {
    this.turnoRepository.excluir(id);
  }
}
