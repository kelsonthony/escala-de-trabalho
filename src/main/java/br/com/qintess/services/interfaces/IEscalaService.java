package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Escala;

import java.util.List;

public interface IEscalaService {

    void salvar(Escala escala);
    List<Escala> listar();
    Escala listarPorId(long id);
    void atualizar(Escala escala);
    void excluir(long id);

}
