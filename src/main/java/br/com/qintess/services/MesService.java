package br.com.qintess.services;

import br.com.qintess.entities.*;
import br.com.qintess.repositories.interfaces.IMesRepository;
import br.com.qintess.services.interfaces.IFeriadoService;
import br.com.qintess.services.interfaces.IFuncionarioService;
import br.com.qintess.services.interfaces.IMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MesService implements IMesService {

  @Autowired
  private IMesRepository mesRepository;

  @Autowired
  private IFuncionarioService funcionarioService;

  @Autowired
  private IFeriadoService feriadoService;

  @Autowired
  private TurnoService turnoService;

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

  private List<Dia> preencimentoAutomaticoDiasDoMes(final LocalDate data, final Turno turnoFuncionario){

    List<Dia> diasMes = new ArrayList<>();

    int totalDiasMes = data.lengthOfMonth();

    LocalDate dataInicial = data;
    LocalDate dataFinal = LocalDate.of(data.getYear(),data.getDayOfMonth(),totalDiasMes);

    List<Feriado> feriadosDoMes = this.feriadoService.listaPorPeriodo(dataInicial,dataFinal);
    List<Turno> turnosPadroesSistema = new ArrayList<>(); // buscar os turnos padões no serviceTurnos;


    for(int i = 0; i <= totalDiasMes; i++){

      int dia = i;

      DayOfWeek diaDaSemana = dataInicial.withDayOfMonth(dia).getDayOfWeek();

      boolean existeFeriadoNoDia = feriadosDoMes.stream().anyMatch(feriado -> feriado.getData().equals(dataInicial.withDayOfMonth(dia)));

      if(existeFeriadoNoDia && turnoFuncionario.getTrabalhaNoFeriado() == 0){

        Dia diaFeriado = new Dia();
        diaFeriado.setDiaDoMes(dia);
        diaFeriado.setTurno(turnosPadroesSistema.stream().filter(turno -> turno.getSigla().equals("FR")).findFirst().get());
        diasMes.add(diaFeriado);
        continue;

      }

      if (folgaNoDia(diaDaSemana,turnoFuncionario.getTurnoFixo())){

        Dia diaFolga = new Dia();
        diaFolga.setDiaDoMes(dia);
        diaFolga.setTurno(turnosPadroesSistema.stream().filter(turno -> turno.getSigla().equals("FG")).findFirst().get());
        diasMes.add(diaFolga);
        continue;

      }

      Dia diaPadrao = new Dia();
      diaPadrao.setDiaDoMes(dia);
      diaPadrao.setTurno(turnoFuncionario);
      diasMes.add(diaPadrao);

  }

    return diasMes;
  }

  private boolean folgaNoDia(final DayOfWeek diaDaSemana, final TurnoFixo turnoFixo){

    switch (diaDaSemana){
      case SUNDAY:
        return turnoFixo.getDomingo() == 0;
      case SATURDAY:
        return turnoFixo.getSabado() == 0;
      case MONDAY:
        return turnoFixo.getSegunda() == 0;
      case TUESDAY:
        return turnoFixo.getTerca() == 0;
      case WEDNESDAY:
        return turnoFixo.getQuarta() == 0;
      case THURSDAY:
        return turnoFixo.getQuinta() == 0;
      case FRIDAY:
        return turnoFixo.getSexta() == 0;
      default:
        return false;
    }

  }

}
