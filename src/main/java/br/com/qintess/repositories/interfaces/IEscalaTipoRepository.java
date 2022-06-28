package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.EscalaTipo;

import java.util.List;

public interface IEscalaTipoRepository {

    void salvar(EscalaTipo escalaTipo);
    List<EscalaTipo> listar();
    List<EscalaTipo> listarPorEscala(long id);
    EscalaTipo listarPorId(long id);
    void atualizar(EscalaTipo escalaTipo);
    void excluir(long id);

}
