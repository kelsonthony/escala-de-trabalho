package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Funcionario;

import java.util.List;

public interface IFuncionarioService {

    void salvar(Funcionario funcionario, long cargoId);
    List<Funcionario> listarPorCargo(long cargoId);
    Funcionario listarPorCargoIdEFuncionarioId(long cargoId, long funcionarioId);
    void atualizar(Funcionario funcionario, long cargoId);
    void excluir(long cargoId, long funcionarioId);
}
