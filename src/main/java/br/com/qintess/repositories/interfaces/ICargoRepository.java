package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Cargo;

import java.util.List;

public interface ICargoRepository {

    void salvar(Cargo cargo);
    List<Cargo> listar();
    Cargo listarPorId(long id);
    void atualizar(Cargo cargo);
    void excluir(long id);
}
