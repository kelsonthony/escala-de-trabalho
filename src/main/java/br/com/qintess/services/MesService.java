package br.com.qintess.services;

import br.com.qintess.entities.Dia;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.entities.Mes;
import br.com.qintess.entities.Turno;
import br.com.qintess.repositories.interfaces.IMesRepository;
import br.com.qintess.services.interfaces.IFuncionarioService;
import br.com.qintess.services.interfaces.IMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MesService implements IMesService {

  @Autowired
  private IMesRepository mesRepository;

  @Autowired
  private IFuncionarioService funcionarioService;

  @Override
  public void salvar(Mes mes) {

  }

  @Override
  public List<Mes> listar() {
    return null;
  }

  @Override
  public Mes listarPorId(long id) {
    return null;
  }

  @Override
  public void atualizar(Mes mes) {

  }

  @Override
  public void excluir(long id) {

  }

  private List<Dia> preencheDiasMes(Mes mes, Turno turno){

    List<Dia> diasMes = new ArrayList<>();

    return null;
  }

}
