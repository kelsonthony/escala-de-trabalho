package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Funcionario;

import java.util.List;

public interface IFuncionarioRepository {

    void salvar(Funcionario funcionario);
    List<Funcionario> listarPorCargo(long cargoId);
    Funcionario listarPorCargoIdEFuncionarioId(long cargoId, long funcionarioId);
    void atualizar(Funcionario funcionario);
    void excluir(long funcionarioId);
}
