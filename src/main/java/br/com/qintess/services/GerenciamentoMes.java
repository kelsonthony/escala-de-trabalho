package br.com.qintess.services;

import br.com.qintess.entities.*;
import br.com.qintess.entities.enumereds.TipoTurnoEnum;
import br.com.qintess.entities.enumereds.TurnosPadroesSistemaEnum;
import br.com.qintess.services.interfaces.IFeriadoService;
import br.com.qintess.services.interfaces.IGerenciamentoMesService;
import br.com.qintess.services.interfaces.IMesService;
import br.com.qintess.services.interfaces.ITurnoService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GerenciamentoMes implements IGerenciamentoMesService {

  private final IMesService mesService;
  private final IFeriadoService feriadoService;
  private final ITurnoService turnoService;

  public GerenciamentoMes(IMesService mesService, IFeriadoService feriadoService, ITurnoService turnoService) {
    this.mesService = mesService;
    this.feriadoService = feriadoService;
    this.turnoService = turnoService;
  }

  @Override
  public void gerarMesPorFuncionario(Funcionario funcionario, Escala escala){

    Mes mes = cadastraUnicoMes(funcionario,escala);

    if(Objects.isNull(mes)){
      throw new RuntimeException("Mês já cadastrado.");
    }


    if(retornaTipoTurno(funcionario) == TipoTurnoEnum.FIXO){

      List<Dia> diasDoMes = gerarDiasTurnoFixo(mes);

      mes.setDias(diasDoMes);
      mes.setTotalHorasNormais(calculaTotalHoras(diasDoMes));

      this.mesService.atualizar(mes);

    }

    if(retornaTipoTurno(funcionario) == TipoTurnoEnum.ALTERNADO){

      List<Dia> diasDoMes = gerarDiasTurnoAlternado(mes);

      mes.setDias(diasDoMes);
      mes.setTotalHorasNormais(calculaTotalHoras(diasDoMes));

      this.mesService.atualizar(mes);

    }


  }


  private Mes cadastraUnicoMes(Funcionario funcionario, Escala escala){

    boolean mesCadastrado = this.mesService.existeMesPorIdFuncionarioIdEscalaEData(funcionario.getFuncionarioId(),escala.getEscalaId(), escala.getData());

    if(mesCadastrado == true){
      return null;
    }

    int totalDiasMes = escala.getData().lengthOfMonth();
    LocalDate dataTerminoMes = escala.getData().withDayOfMonth(totalDiasMes);

    Mes novoMes = new Mes(escala.getData().getMonthValue(),escala.getData(),dataTerminoMes,escala,funcionario);

    return this.mesService.salvar(novoMes);

  }

  private List<Dia> gerarDiasTurnoFixo(Mes mes){

    Turno turnoFuncionario = mes.getFuncionario().getTurno();
    int totalDiasMes = mes.getDataInicio().lengthOfMonth();

    List<Dia> dias = new ArrayList<>();
    List<Feriado> feriados = this.feriadoService.listaPorPeriodo(mes.getDataInicio(),mes.getDataTermino());
    List<Turno> turnosSistema = this.turnoService.listarPorPadraoSistema();

    for(int i = 0; i < totalDiasMes; i++){

      int diaAtual = 1 + i;

      LocalDate dataDoDia = mes.getDataInicio().withDayOfMonth(diaAtual);

      Dia novoDia = new Dia();
      novoDia.setMes(mes);
      novoDia.setDiaDoMes(diaAtual);

      if(diaPossuiFeriado(dataDoDia,feriados) && turnoFuncionario.getTrabalhaNoFeriado() == 0){

        Turno turnoFeriado = retornaTurnoPadrao(TurnosPadroesSistemaEnum.FERIADO.getSigla(),turnosSistema);

        novoDia.setTurno(turnoFeriado);
        dias.add(novoDia);

        continue;

      }

      if( folgaNoDia(dataDoDia.getDayOfWeek(),turnoFuncionario.getTurnoFixo()) ){

        Turno turnoFolga = retornaTurnoPadrao(TurnosPadroesSistemaEnum.FOLGA.getSigla(),turnosSistema);

        novoDia.setTurno(turnoFolga);
        dias.add(novoDia);

        continue;

      }

      novoDia.setTurno(turnoFuncionario);
      dias.add(novoDia);

    }

    return dias;

  }

  private List<Dia> gerarDiasTurnoAlternado(Mes mes){

    Turno turnoFuncionario = mes.getFuncionario().getTurno();
    int totalDiasMes = mes.getDataInicio().lengthOfMonth();

    List<Dia> dias = new ArrayList<>();
    List<Feriado> feriados = this.feriadoService.listaPorPeriodo(mes.getDataInicio(),mes.getDataTermino());
    List<Turno> turnosSistema = this.turnoService.listarPorPadraoSistema();

    int contadorDiasTrabalhados = 0;
    int contadorDiasFolga = 0;

    for(int i = 0; i < totalDiasMes; i++){

      int diaAtual = 1 + i;

      LocalDate dataDoDia = mes.getDataInicio().withDayOfMonth(diaAtual);

      Dia novoDia = new Dia();
      novoDia.setMes(mes);
      novoDia.setDiaDoMes(diaAtual);

      if(diaPossuiFeriado(dataDoDia,feriados) && turnoFuncionario.getTrabalhaNoFeriado() == 0){

        Turno turnoFeriado = retornaTurnoPadrao(TurnosPadroesSistemaEnum.FERIADO.getSigla(),turnosSistema);

        novoDia.setTurno(turnoFeriado);
        dias.add(novoDia);

        continue;

      }

      if(contadorDiasTrabalhados == turnoFuncionario.getTurnoAlternado().getQuantDiasConsecutivosTrabalho()){

        if(contadorDiasFolga < turnoFuncionario.getTurnoAlternado().getQuantDiasFolga()){

          Turno turnoFolga = retornaTurnoPadrao(TurnosPadroesSistemaEnum.FOLGA.getSigla(),turnosSistema);

          novoDia.setTurno(turnoFolga);
          dias.add(novoDia);
          contadorDiasFolga++;
          continue;
        }

        if(contadorDiasFolga == turnoFuncionario.getTurnoAlternado().getQuantDiasFolga()){

          contadorDiasFolga = 0;
          contadorDiasTrabalhados = 0;

        }

      }

      novoDia.setTurno(turnoFuncionario);
      dias.add(novoDia);
      contadorDiasTrabalhados ++;

    }

    return dias;
  }

  private TipoTurnoEnum retornaTipoTurno(Funcionario funcionario){

    return ( !Objects.isNull(funcionario.getTurno().getTurnoFixo()) ) ? TipoTurnoEnum.FIXO : TipoTurnoEnum.ALTERNADO;

  }

  private Turno retornaTurnoPadrao(String sigla, List<Turno> turnos){
    return turnos.stream().filter(turno -> turno.getSigla().equals(sigla)).findFirst().get();
  }

  private boolean diaPossuiFeriado(LocalDate dataDoDia, List<Feriado> feriados){
    return feriados.stream().anyMatch(feriado -> feriado.getData().equals(dataDoDia));
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

  private long calculaTotalHoras(List<Dia> dias){

    int TotalHorasEmSegundos = 0;

    for (Dia dia: dias) {

      int segundosDia = LocalTime.parse(dia.getTurno().getTotalHoras()).toSecondOfDay();
      TotalHorasEmSegundos += segundosDia;

    }

    return (TotalHorasEmSegundos / 60)/60;

  }

}
