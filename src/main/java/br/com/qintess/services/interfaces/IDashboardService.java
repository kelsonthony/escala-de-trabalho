package br.com.qintess.services.interfaces;

import br.com.qintess.entities.DashboardDto;
import br.com.qintess.entities.Mes;

import java.time.LocalDate;
import java.util.List;

public interface IDashboardService {

    List<DashboardDto> listar(List<Mes> meses);

    String titulo(LocalDate data);

    List<LocalDate> dias(LocalDate data);

    int totalDiasDoMes(int ano, int mes);
}
