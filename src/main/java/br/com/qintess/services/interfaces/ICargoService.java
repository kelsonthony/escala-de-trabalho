package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Cargo;

import java.util.List;

public interface ICargoService {

    void salvar(Cargo cargo);
    List<Cargo> listar();
    Cargo listarPorId(long id);
    void atualizar(Cargo cargo);
    void excluir(long id);

}
