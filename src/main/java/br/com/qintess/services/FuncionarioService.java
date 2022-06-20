package br.com.qintess.services;

import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.repositories.interfaces.IFuncionarioRepository;
import br.com.qintess.services.interfaces.ICargoService;
import br.com.qintess.services.interfaces.IFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FuncionarioService implements IFuncionarioService {

    @Autowired
    private IFuncionarioRepository funcionarioRepository;

    @Autowired
    private ICargoService cargoService;

    @Override
    public void salvar(Funcionario funcionario, long cargoId) {
        funcionario.setCargo(cargoService.listarPorId(cargoId));
        funcionarioRepository.salvar(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listar() {
        return funcionarioRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listarPorCargo(long cargoId) {
        return funcionarioRepository.listarPorCargo(cargoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionario listarPorCargoIdEFuncionarioId(long cargoId, long funcionarioId) {
        return funcionarioRepository.listarPorCargoIdEFuncionarioId(cargoId, funcionarioId);
    }

    @Override
    public void atualizar(Funcionario funcionario, long cargoId) {
        funcionario.setCargo(cargoService.listarPorId(cargoId));
        funcionarioRepository.atualizar(funcionario);
    }

    @Override
    public void excluir(long cargoId, long funcionarioId) {
        funcionarioRepository.excluir(listarPorCargoIdEFuncionarioId(cargoId, funcionarioId).getId());
    }
}
