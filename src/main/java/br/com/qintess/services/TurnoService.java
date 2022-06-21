package br.com.qintess.services;

import br.com.qintess.entities.Turno;
import br.com.qintess.repositories.TurnoRepository;
import br.com.qintess.services.interfaces.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class TurnoService implements ITurnoService {

  @Autowired
  private TurnoRepository turnoRepository;

  @Override
  public void salvar(Turno turno) {

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

