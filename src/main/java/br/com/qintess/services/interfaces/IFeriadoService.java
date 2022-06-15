package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Feriado;

import java.util.List;

public interface IFeriadoService {

    void save(Feriado feriado);
    Feriado listarPorId(Integer id);
    List<Feriado> listar();
    void atualizar(Feriado feriado);
    void excluirPorId(Integer id);
}
