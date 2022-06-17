package br.com.qintess.services;

import br.com.qintess.entities.Feriado;
import br.com.qintess.repositories.interfaces.IFeriadoRepository;
import br.com.qintess.services.interfaces.IFeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeriadoService implements IFeriadoService {

  @Autowired
  private IFeriadoRepository feriadoRepository;


  @Override
  public void salvar(final Feriado feriado) {

    this.feriadoRepository.salvar(feriado);

  }

  @Override
  public List<Feriado> listar() {
    return this.feriadoRepository.listar();
  }

  @Override
  public Feriado listarPorId(final Integer id) {
    return this.feriadoRepository.listarPorId(id);
  }


  @Override
  public void atualizar( final Feriado feriado) {

    this.feriadoRepository.atualizar(feriado);

  }

  @Override
  public void excluirPorId(final Integer id) {

    this.feriadoRepository.excluir(id);

  }
}
