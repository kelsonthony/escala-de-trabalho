package br.com.qintess.services;

import br.com.qintess.entities.DashboardDto;
import br.com.qintess.entities.Mes;
import br.com.qintess.repositories.interfaces.IDashboardRepository;
import br.com.qintess.repositories.interfaces.IMesRepository;
import br.com.qintess.services.interfaces.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService implements IDashboardService {

    @Autowired
    IDashboardRepository dashboardRepository;

    @Autowired
    IMesRepository mesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DashboardDto> listar(List<Mes> meses) {
        return dashboardRepository.listar(meses);
    }

    @Override
    public String titulo(Mes mes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/yyyy");
        return "Escala " + mes.getEscala().getNome() + " de " + mes.getEscala().getData().format(formatter);

    }

    @Override
    public List<LocalDate> dias(Mes m) {
        int ano = m.getDataInicio().getYear();
        int mes = m.getDataInicio().getMonthValue();

        List<LocalDate> dias = new ArrayList<>();
        int totalDiasDoMes = this.totalDiasDoMes(ano, mes);
        for (int i = 0; i < totalDiasDoMes; i++) {
            dias.add(m.getDataInicio().plusDays(i));
        }

        return dias;
    }

    @Override
    public int totalDiasDoMes(int ano, int mes) {
        return YearMonth.of(ano, mes).lengthOfMonth();
    }
}
