package br.com.qintess.repositories;

import br.com.qintess.entities.DashboardDto;
import br.com.qintess.entities.Mes;
import br.com.qintess.repositories.interfaces.IDashboardRepository;
import br.com.qintess.repositories.interfaces.IMesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DashboardRepository implements IDashboardRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IMesRepository mesRepository;

    @Override
    public List<DashboardDto> listar(List<Mes> meses) {

        List<DashboardDto> dtos = new ArrayList<DashboardDto>();

        for (Mes mes : meses) {
            DashboardDto dto = new DashboardDto(mes);
            dtos.add(dto);
        }
        return dtos;
    }
}
