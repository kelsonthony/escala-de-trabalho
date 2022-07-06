package br.com.qintess.services;

import br.com.qintess.entities.*;
import br.com.qintess.services.interfaces.IFeriadoService;
import br.com.qintess.services.interfaces.IGerenciamentoMesFixoService;
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
public class GerenciamentoMesFixo implements IGerenciamentoMesFixoService {

  private final IMesService mesService;
  private final IFeriadoService feriadoService;
  private final ITurnoService turnoService;


  public GerenciamentoMesFixo(IMesService mesService, IFeriadoService feriadoService, ITurnoService turnoService) {
    this.mesService = mesService;
    this.feriadoService = feriadoService;
    this.turnoService = turnoService;
  }

  @Override
  public void cadastrarMes(Funcionario funcionario, Escala escala){

    int totalDiasMes = escala.getData().lengthOfMonth();
    LocalDate dataInicio = escala.getData().withDayOfMonth(1);
    LocalDate dataTermino = dataInicio.withDayOfMonth(totalDiasMes);

    List<Mes> mesExiste = this.mesService.listarPorIdFuncionarioEDataInicio(funcionario.getFuncionarioId(),dataInicio);

    if(!mesExiste.isEmpty()){
      throw new RuntimeException("Erro ao tentar salvar mês (#Mês já cadastrado).");
    }

    Mes novoMes = new Mes(escala.getData().getMonthValue(),dataInicio,dataTermino,escala,funcionario);

    Mes mesCadastrado = this.mesService.salvar(novoMes);

    List<Dia> dias = preenchimentoAutomaticoDias(mesCadastrado);
    mesCadastrado.setTotalHorasNormais(calculaTotalHorasNormais(dias));
    mesCadastrado.setDias(dias);

    this.mesService.atualizar(mesCadastrado);

  }

  @Override
  public void cadastrarListaMeses(Escala escala) {

    List<Funcionario> funcionarios = escala.getFuncionarios();

    for(Funcionario funcionario: funcionarios) {

      if(verificaTipoTurnoFuncionario(funcionario) == 1){

        int totalDiasMes = escala.getData().lengthOfMonth();
        LocalDate dataInicio = escala.getData().withDayOfMonth(1);
        LocalDate dataTermino = dataInicio.withDayOfMonth(totalDiasMes);

        List<Mes> mesExiste = this.mesService.listarPorIdFuncionarioEDataInicio(funcionario.getFuncionarioId(),dataInicio);

        if(!mesExiste.isEmpty()){
          continue;
        }

        Mes novoMes = new Mes(escala.getData().getMonthValue(),dataInicio,dataTermino,escala,funcionario);

        Mes mesCadastrado = this.mesService.salvar(novoMes);

        List<Dia> dias = preenchimentoAutomaticoDias(mesCadastrado);
        mesCadastrado.setTotalHorasNormais(calculaTotalHorasNormais(dias));
        mesCadastrado.setDias(dias);

        this.mesService.atualizar(mesCadastrado);

      }

      if(verificaTipoTurnoFuncionario(funcionario) == 0){
        List<Mes> meses = this.mesService.listarPorIdFuncionarioEDataInicio(funcionario.getFuncionarioId(),escala.getData().withDayOfMonth(1));

        if(meses.isEmpty()){

          Mes mes = new Mes();
          mes.setDataInicio(escala.getData().withDayOfMonth(1));
          mes.setDataTermino(mes.getDataInicio().withDayOfMonth(escala.getData().lengthOfMonth()));
          mes.setFuncionario(funcionario);
          mes.setEscala(escala);

          Mes mesCadastrado = this.mesService.salvar(mes);

          List<Dia> dias = preenchimentoAutomaticoDiasAlternado(mes);
          mesCadastrado.setDias(dias);
          this.mesService.atualizar(mesCadastrado);

        }

      }

    }


  }

  public List<Dia> preenchimentoAutomaticoDiasAlternado(Mes mes){

    Turno turnoFuncionario = mes.getFuncionario().getTurno();
    int totalDiasMes = mes.getDataInicio().lengthOfMonth();
    Funcionario funcionario = mes.getFuncionario();

    List<Dia> listaDias = new ArrayList<>();
    List<Feriado> feriados = this.feriadoService.listaPorPeriodo(mes.getDataInicio(),mes.getDataTermino());
    List<Turno> turnosPadroesSistema = this.turnoService.listarPorPadraoSistema();

    int diasTrabalhados = 0;

    for(int i = 0; i < mes.getDataInicio().lengthOfMonth(); i++){

      int diaDoMes = 1 + i;
      LocalDate data = mes.getDataInicio().withDayOfMonth(diaDoMes);

      Dia dia = new Dia();
      dia.setDiaDoMes(diaDoMes);
      dia.setMes(mes);

      if(existeFeriadoNoData(feriados,data) && turnoFuncionario.getTrabalhaNoFeriado() == 0){

        dia.setTurno(localizaTurnoPadrao("FR",turnosPadroesSistema));
        listaDias.add(dia);
        continue;
      }

      if(diasTrabalhados == turnoFuncionario.getTurnoAlternado().getQuantDiasConsecutivosTrabalho()){

        dia.setTurno(localizaTurnoPadrao("FG",turnosPadroesSistema));
        listaDias.add(dia);
        diasTrabalhados = 0;
        continue;

      }

      dia.setTurno(turnoFuncionario);
      listaDias.add(dia);
      diasTrabalhados = diasTrabalhados + 1;


    }

    return listaDias;
  }

  private List<Dia> preenchimentoAutomaticoDias(Mes mes){

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

  private long calculaTotalHorasTrabalhadas(List<Dia> dias){

    int totalHorasEmSegundos = 0;

    for (Dia dia: dias) {

      LocalTime horaTurno = LocalTime.parse(dia.getTurno().getTotalHoras());

      int totalSegundos = horaTurno.toSecondOfDay();
      totalHorasEmSegundos += totalSegundos;
    }

    return (totalHorasEmSegundos * 60) * 60;

  }

  private long calculaTotalHorasNormais(List<Dia> dias){

    int TotalHorasEmSegundos = 0;

    for (Dia dia: dias) {

      int segundosDia = LocalTime.parse(dia.getTurno().getTotalHoras()).toSecondOfDay();
      TotalHorasEmSegundos += segundosDia;

    }

    return (TotalHorasEmSegundos / 60)/60;

  }

  private int verificaTipoTurnoFuncionario(Funcionario funcionario){

    TurnoFixo turnoFixo = funcionario.getTurno().getTurnoFixo();
    TurnoAlternado turnoAlternado = funcionario.getTurno().getTurnoAlternado();

    if(!Objects.isNull(turnoFixo)){
      return 1;
    }else {
      return 0;
    }

  }


}
