package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Funcionario;

import java.util.List;

public interface IFuncionarioService {

    void salvar(Funcionario funcionario, long cargoId);
    List<Funcionario> listar();
    Funcionario listarPorId(long id);
    List<Funcionario> listarPorCargo(long cargoId);
    List<Funcionario> listarPorEquipe(long equipeId);
    List<Funcionario> listarPorTurno(long turnoId);
    Funcionario listarPorCargoIdEFuncionarioId(long cargoId, long funcionarioId);
    void atualizar(Funcionario funcionario, long cargoId);
    void excluir(long cargoId, long funcionarioId);
}
