package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Escala;

import java.time.LocalDate;
import java.util.List;

public interface IEscalaService {

    void salvar(Escala escala);
    List<Escala> listar();
    LocalDate listarUltimaEscala();
    Escala listarPorId(long id);
    void atualizar(Escala escala);
    void excluir(long id);
    void excluirTipo(long id);

}
