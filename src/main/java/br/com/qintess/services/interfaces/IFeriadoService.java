package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Feriado;

import java.util.List;

public interface IFeriadoService {

    void salvar(Feriado feriado);
    Feriado listarPorId(long id);
    List<Feriado> listar();
    void atualizar(Feriado feriado);
    void excluir(long id);
}
