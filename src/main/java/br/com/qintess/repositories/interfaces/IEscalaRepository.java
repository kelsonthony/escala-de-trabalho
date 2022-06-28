package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Escala;

import java.util.List;

public interface IEscalaRepository {

    void salvar(Escala escala);
    List<Escala> listar();
    Escala listarPorId(long id);
    void atualizar(Escala escala);
    void excluir(long id);
}
