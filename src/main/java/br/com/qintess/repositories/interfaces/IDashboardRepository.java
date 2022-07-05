package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.DashboardDto;
import br.com.qintess.entities.Mes;

import java.util.List;

public interface IDashboardRepository {
    List<DashboardDto> listar(List<Mes> meses);
}
