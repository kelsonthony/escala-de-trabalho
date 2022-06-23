package br.com.qintess.services;

import br.com.qintess.entities.Funcionario;
import br.com.qintess.repositories.interfaces.IFuncionarioRepository;
import br.com.qintess.services.interfaces.ICargoService;
import br.com.qintess.services.interfaces.IEquipeService;
import br.com.qintess.services.interfaces.IFuncionarioService;
import br.com.qintess.services.interfaces.ITurnoService;
import org.hibernate.exception.ConstraintViolationException;
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

    @Autowired
    private IEquipeService equipeService;

    @Autowired
    private ITurnoService turnoService;

    @Override
    public void salvar(Funcionario funcionario) {
        if (funcionario.equals(null)) {
            throw new ConstraintViolationException(
                    "Erro ao tentar salvar o funcionário (#Objeto vazio).", null, null);
        }
        funcionarioRepository.salvar(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listar() {
        return funcionarioRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionario listarPorId(final long id) {
        return funcionarioRepository.listarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listarPorCargo(final long cargoId) {
        return funcionarioRepository.listarPorCargo(cargoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listarPorEquipe(final long equipeId) {
        return funcionarioRepository.listarPorEquipe(equipeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listarPorTurno(final long turnoId) {
        return funcionarioRepository.listarPorTurno(turnoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionario listarPorCargoIdEFuncionarioId(final long cargoId, final long funcionarioId) {
        return funcionarioRepository.listarPorCargoIdEFuncionarioId(cargoId, funcionarioId);
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        funcionarioRepository.atualizar(funcionario);
    }

    @Override
    public void excluir(final long id) {
        Funcionario funcionario = funcionarioRepository.listarPorId(id);
        if (funcionario.equals(null)) {
            throw new ConstraintViolationException(
                    "Erro ao tentar remover o funcionário (#Id não existe).", null, null);
        }
        funcionarioRepository.excluir(id);
    }
}
