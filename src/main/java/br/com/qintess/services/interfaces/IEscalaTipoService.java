package br.com.qintess.services.interfaces;


import br.com.qintess.entities.EscalaTipo;

import java.util.List;

public interface IEscalaTipoService {

    void salvar(EscalaTipo escalaTipo);
    List<EscalaTipo> listar();
    EscalaTipo listarPorId(long id);
    List<EscalaTipo> listarPorEscala(long id);
    void atualizar(EscalaTipo escalaTipo);
    void excluir(long id);
}
