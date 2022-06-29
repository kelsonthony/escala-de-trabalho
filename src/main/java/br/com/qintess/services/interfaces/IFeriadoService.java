package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Feriado;

import java.time.LocalDate;
import java.util.List;

public interface IFeriadoService {

    void salvar(Feriado feriado);
    Feriado listarPorId(long id);
    List<Feriado> listar();
    List<Feriado> listaPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
    void atualizar(Feriado feriado);
    void excluir(long id);
}
