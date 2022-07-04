package br.com.qintess.services;

import br.com.qintess.entities.*;
import br.com.qintess.services.interfaces.IFeriadoService;
import br.com.qintess.services.interfaces.IGerenciamentoMesFixoService;
import br.com.qintess.services.interfaces.IMesService;
import br.com.qintess.services.interfaces.ITurnoService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GerenciamentoMesFixo implements IGerenciamentoMesFixoService {

  private final IMesService mesService;
  private final IFeriadoService feriadoService;
  private final ITurnoService turnoService;


  public GerenciamentoMesFixo(IMesService mesService, IFeriadoService feriadoService, ITurnoService turnoService) {
    this.mesService = mesService;
    this.feriadoService = feriadoService;
    this.turnoService = turnoService;
  }

  public void cadastrarMes(Funcionario funcionario, Escala escala){

    int totalDiasMes = escala.getData().lengthOfMonth();
    LocalDate dataInicio = escala.getData().withDayOfMonth(1);
    LocalDate dataTermino = dataInicio.withDayOfMonth(totalDiasMes);

    /*Mes mesSalvo = this.mesService.listarPorIdFuncionarioEDataInicio(funcionario.getFuncionarioId(),dataInicio);

    if(!Objects.isNull(mesSalvo)){
      throw  new RuntimeException("Mes já esta cadastrado.");
    }*/

    Mes novoMes = new Mes(escala.getData().getMonthValue(),dataInicio,dataTermino,escala,funcionario);

    Mes mesCadastrado = this.mesService.salvar(novoMes);

    mesCadastrado.setDias(preencimentoAutomaticoDias(mesCadastrado));

    this.mesService.atualizar(mesCadastrado);

  }

  public void cadastrarListaMeses(List<Mes> meses){

    for (Mes mes : meses) {

      Mes novoMes = this.mesService.salvar(mes);
      List<Dia> diasDoMes = preencimentoAutomaticoDias(mes);
      novoMes.setDias(diasDoMes);

      this.mesService.atualizar(novoMes);

    }


  }

  private List<Dia> preencimentoAutomaticoDias(Mes mes){

    Turno turnoFuncionario = mes.getFuncionario().getTurno();
    int totalDiasMes = mes.getEscala().getData().lengthOfMonth();

    List<Dia> diasMes = new ArrayList<>();
    List<Feriado> feriados = this.feriadoService.listaPorPeriodo(mes.getDataInicio(),mes.getDataTermino());
    List<Turno> turnosPadroesSistema = this.turnoService.listarPorPadraoSistema();


    for(int i = 0; i < totalDiasMes; i++){

      int numeroDia = 1 + i;

      Dia diaAtual = new Dia();
      diaAtual.setMes(mes);
      diaAtual.setDiaDoMes(numeroDia);

      LocalDate dataAtual = mes.getDataInicio().withDayOfMonth(numeroDia);
      DayOfWeek diaDaSemana = mes.getDataInicio().withDayOfMonth(numeroDia).getDayOfWeek();

      if(existeFeriadoNoData(feriados,dataAtual) && turnoFuncionario.getTrabalhaNoFeriado() == 0){

        diaAtual.setTurno(localizaTurnoPadrao("FR",turnosPadroesSistema));
        diasMes.add(diaAtual);
        continue;

      }

      if (folgaNoDia(diaDaSemana,turnoFuncionario.getTurnoFixo())){

        diaAtual.setTurno(localizaTurnoPadrao("FG",turnosPadroesSistema));
        diasMes.add(diaAtual);
        continue;

      }

      diaAtual.setTurno(turnoFuncionario);
      diasMes.add(diaAtual);
      continue;
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

  private boolean existeFeriadoNoData(List<Feriado> feriados, LocalDate data){
    return feriados.stream().anyMatch(feriado -> feriado.getData().equals(data));
  }

  private Turno localizaTurnoPadrao(String sigla, List<Turno> turnos){
    return turnos.stream().filter(turno -> turno.getSigla().equals(sigla)).findFirst().get();
  }


}
