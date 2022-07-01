package br.com.qintess.services;

import br.com.qintess.entities.*;
import br.com.qintess.repositories.interfaces.IMesRepository;
import br.com.qintess.services.interfaces.IMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
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
    return this.mesRepository.listarPorId(id);
  }

  @Override
  public Mes listarPorIdFuncionarioEDataInicio(long idFuncionario, LocalDate dateInicio) {
    return this.mesRepository.listarPorIdFuncionarioEDataInicio(idFuncionario,dateInicio);
  }

  @Override
  public void atualizar(final Mes mes) {this.mesRepository.atualizar(mes);}

  @Override
  public void excluir(final long id) {
    this.mesRepository.excluir(id);

  }


}
