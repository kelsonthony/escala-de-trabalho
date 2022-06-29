package br.com.qintess.services;

import br.com.qintess.entities.Turno;
import br.com.qintess.repositories.TurnoRepository;
import br.com.qintess.repositories.interfaces.IFuncionarioRepository;
import br.com.qintess.services.interfaces.ITurnoService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class TurnoService implements ITurnoService {

  @Autowired
  private TurnoRepository turnoRepository;

  @Autowired
  IFuncionarioRepository funcionarioRepository;

  @Override
  public void salvar(final Turno turno) {

    if (turno.equals(null)) {
      throw new ConstraintViolationException("Erro ao tentar salvar o turno (#Objeto vazio).", null, null);
    }

    List<Turno> turnosExistentes = this.turnoRepository.listaPorSigla(turno.getSigla());

    if (!turnosExistentes.isEmpty()){
      throw new ConstraintViolationException("Erro ao tentar salvar o turno(#Sigla já cadastrada).",null,null);
    }

    turno.setTotalHoras(calculaTotalHoras(turno.getHoraInicio(),turno.getHoraTermino()));
    this.turnoRepository.salvar(turno);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Turno> listar() {
    return this.turnoRepository.listar();
  }

  @Override
  @Transactional(readOnly = true)
  public Turno listarPorId(final long id) {
    return this.turnoRepository.listarPorId(id);
  }

  @Override
  public List<Turno> listarPorPadraoSistema() {
    return this.turnoRepository.listarPorPadraoSistema();
  }

  @Override
  public void atualizar(Turno turno) {
    turno.setTotalHoras(calculaTotalHoras(turno.getHoraInicio(),turno.getHoraTermino()));
    this.turnoRepository.atualizar(turno);
  }

  @Override
  public void excluir(final long id) {
    Turno turno = turnoRepository.listarPorId(id);
    boolean temFuncionarios = funcionarioRepository.temFuncionarios("turno", id);

    if (turno.equals(null))
      throw new ConstraintViolationException("Erro ao tentar remover o turno (#Id não existe).", null, null);

    if (temFuncionarios){
      throw new ConstraintViolationException(
              "Não é possível remover um turno que possui funcionários cadastrados.", null, null);
    }

    this.turnoRepository.excluir(id);
  }

  private String calculaTotalHoras(String horaInicio, String horaTermino){

    int totalSegundosDia = 86400;
    int horaInicioSegundos = LocalTime.parse(horaInicio).toSecondOfDay();
    int horaTerminoSegundos = LocalTime.parse(horaTermino).toSecondOfDay();

    if(horaTerminoSegundos < horaInicioSegundos){

      int totalSegundos = (totalSegundosDia - horaInicioSegundos) + horaTerminoSegundos;
      String totalEmHoras = LocalTime.ofSecondOfDay(totalSegundos).toString();

      return totalEmHoras;

    }

    if(horaTerminoSegundos == horaInicioSegundos){
      return "24:00";
    }

    int totalSegundos = horaTerminoSegundos - horaInicioSegundos;

    return LocalTime.ofSecondOfDay(totalSegundos).toString();
  }

}

