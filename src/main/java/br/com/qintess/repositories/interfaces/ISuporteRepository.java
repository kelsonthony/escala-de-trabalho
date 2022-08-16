package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Suporte;

import java.util.List;

public interface ISuporteRepository {

    void salvar(Suporte suporte);
    List<Suporte> listar();

}
