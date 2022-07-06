package br.com.qintess.services.interfaces;

import br.com.qintess.entities.DashboardDto;
import br.com.qintess.entities.Mes;

import java.time.LocalDate;
import java.util.List;

public interface IDashboardService {

    List<DashboardDto> listar(List<Mes> meses);

    String titulo(Mes mes);

    List<LocalDate> dias(Mes mes);

    int totalDiasDoMes(int ano, int mes);
}
