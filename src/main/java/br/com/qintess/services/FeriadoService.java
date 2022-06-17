package br.com.qintess.services;

import br.com.qintess.entities.Feriado;
import br.com.qintess.repositories.interfaces.IFeriadoRepository;
import br.com.qintess.services.interfaces.IFeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;


@Service
public class FeriadoService implements IFeriadoService {

  @Autowired
  private IFeriadoRepository feriadoRepository;


  @Override
  @Transactional
  public void salvar(Feriado feriado) {

    String diaDaSemana = feriado.getData().getDayOfWeek().getDisplayName(TextStyle.FULL,new Locale("pt", "BR"));
    feriado.setDiaSemana(diaDaSemana);
    this.feriadoRepository.salvar(feriado);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Feriado> listar() {
    return this.feriadoRepository.listar();
  }

  @Override
  @Transactional(readOnly = true)
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
