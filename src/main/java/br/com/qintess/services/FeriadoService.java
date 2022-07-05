package br.com.qintess.services;

import br.com.qintess.entities.Feriado;
import br.com.qintess.repositories.interfaces.IFeriadoRepository;
import br.com.qintess.services.interfaces.IFeriadoService;
import org.hibernate.exception.ConstraintViolationException;
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
    if (feriado.equals(null)) {
      throw new ConstraintViolationException(
              "Erro ao tentar salvar o feriado (#Objeto vazio).", null, null);
    }
    feriado.setDiaSemana(retornaDiaDaSemana(feriado.getData()));
    this.feriadoRepository.salvar(feriado);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Feriado> listar() {
    return this.feriadoRepository.listar();
  }

  @Override
  public List<Feriado> listaPorPeriodo(final LocalDate dataInicio, final LocalDate dataFim) {
    return this.feriadoRepository.listaPorPeriodo(dataInicio,dataFim);
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
    Feriado feriado = feriadoRepository.listarPorId(id);
    if (feriado.equals(null)) {
      throw new ConstraintViolationException("Erro ao tentar remover o feriado (#Id não existe).", null, null);
    }
    this.feriadoRepository.excluir(id);
  }

  private String retornaDiaDaSemana(LocalDate date){
    String diaDaSemana = date.getDayOfWeek().getDisplayName(TextStyle.FULL,new Locale("pt", "BR"));
    return diaDaSemana;
  }
}
