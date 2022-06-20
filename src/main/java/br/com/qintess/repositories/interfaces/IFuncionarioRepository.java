package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Funcionario;

import java.util.List;

public interface IFuncionarioRepository {

    void salvar(Funcionario funcionario);
    List<Funcionario> listar();
    Funcionario listarPorId(long id);
    List<Funcionario> listarPorCargo(long cargoId);
    List<Funcionario> listarPorEquipe(long equipeId);
    List<Funcionario> listarPorTurno(long turnoId);
    Funcionario listarPorCargoIdEFuncionarioId(long cargoId, long funcionarioId);
    void atualizar(Funcionario funcionario);
    void excluir(long funcionarioId);
}
