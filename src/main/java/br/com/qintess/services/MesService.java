package br.com.qintess.services;

import br.com.qintess.entities.*;
import br.com.qintess.repositories.interfaces.IMesRepository;
import br.com.qintess.services.interfaces.IMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class MesService implements IMesService {

  @Autowired
  private IMesRepository mesRepository;

  @Override
  public Mes salvar(final Mes mes) {
   return this.mesRepository.salvar(mes);
  }

  @Override
  public List<Mes> listar() {
    return this.mesRepository.listar();
  }

  @Override
  public Mes listarPorId(final long id) {
    Mes mes = this.mesRepository.listarPorId(id);
    if (mes.equals(null)) {
      throw new NullPointerException("Esta escala ainda não possui funcionários cadastrados.");
    }

    return mes;
  }

  @Override
  public List<Mes> listarPorIdFuncionarioEDataInicio(long idFuncionario, LocalDate dateInicio) {
    return this.mesRepository.listarPorIdFuncionarioEDataInicio(idFuncionario,dateInicio);
  }

  @Override
  public boolean existeMesPorIdFuncionarioIdEscalaEData(long idFuncionario, long idEscala, LocalDate dataInicio) {
    return this.mesRepository.existeMesPorIdFuncionarioIdEscalaEData(idFuncionario,idEscala,dataInicio);
  }

  @Override
  public List<Mes> listarPorData(LocalDate dataInicio) {
    return this.mesRepository.listarPorData(dataInicio);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Mes> listarPorEscala(final long id) {
    return this.mesRepository.listarPorEscala(id);
  }

  @Override
  public List<Mes> listarPorEscalaEData(long idEscala, String data) {
    return this.mesRepository.listarPorEscalaEData(idEscala,data);
  }

  @Override
  public void atualizar(final Mes mes) {this.mesRepository.atualizar(mes);}

  @Override
  public void excluir(final long id) {
    this.mesRepository.excluir(id);

  }

  @Override
  public List<String> dias(LocalDate data) {
    int ano = data.getYear();
    int mes = data.getMonthValue();

    List<String> dias = null;
    int totalDiasDoMes = this.totalDiasDoMes(ano, mes);
    for (int i = 0; i < totalDiasDoMes; i++) {
      dias.add(data.plusDays(i).getDayOfWeek().toString() + "(" + data.plusDays(i).getDayOfMonth() + ")");
    }

    return dias;
  }

  public int totalDiasDoMes(int ano, int mes) {
    return YearMonth.of(ano, mes).lengthOfMonth();
  }

}
