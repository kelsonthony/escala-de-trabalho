package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Feriado;

import java.util.List;

public interface IFeriadoService {

    Feriado save(Feriado feriado);
    Feriado findById(Integer id);
    List<Feriado> findAll();
    void deleteById(Integer id);
}
