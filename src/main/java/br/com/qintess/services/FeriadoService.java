package br.com.qintess.services;

import br.com.qintess.entities.Feriado;
import br.com.qintess.repositories.interfaces.IFeriadoRepository;
import br.com.qintess.services.interfaces.IFeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    feriado.setDiaSemana(retornaDiaDaSemana(feriado.getData()));
    this.feriadoRepository.salvar(feriado);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Feriado> listar() {
    return this.feriadoRepository.listar();
  }

  @Override
  @Transactional(readOnly = true)
  public Feriado listarPorId(final long id) {
    return this.feriadoRepository.listarPorId(id);
  }


  @Override
  @Transactional
  public void atualizar( final Feriado feriado) {

    feriado.setDiaSemana(retornaDiaDaSemana(feriado.getData()));
    this.feriadoRepository.atualizar(feriado);

  }

  @Override
  @Transactional
  public void excluir(final long id) {

    this.feriadoRepository.excluir(id);

  }

  private String retornaDiaDaSemana(LocalDate date){
    String diaDaSemana = date.getDayOfWeek().getDisplayName(TextStyle.FULL,new Locale("pt", "BR"));
    return diaDaSemana;
  }
}
