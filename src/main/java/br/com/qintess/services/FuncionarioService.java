package br.com.qintess.services;

import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.repositories.interfaces.ICargoRepository;
import br.com.qintess.repositories.interfaces.IEquipeRepository;
import br.com.qintess.repositories.interfaces.IFuncionarioRepository;
import br.com.qintess.repositories.interfaces.ITurnoRepository;
import br.com.qintess.services.interfaces.ICargoService;
import br.com.qintess.services.interfaces.IEquipeService;
import br.com.qintess.services.interfaces.IFuncionarioService;
import br.com.qintess.services.interfaces.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FuncionarioService implements IFuncionarioService {

    @Autowired
    private IFuncionarioRepository funcionarioRepository;

    @Autowired
    private ICargoRepository cargoRepository;

    @Autowired
    private IEquipeRepository equipeRepository;

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private ICargoService cargoService;

    @Autowired
    private IEquipeService equipeService;

    @Autowired
    private ITurnoService turnoService;

    @Override
    public void salvar(Funcionario funcionario) {
        funcionarioRepository.salvar(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listar() {
        return funcionarioRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionario listarPorId(long id) {
        return funcionarioRepository.listarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listarPorCargo(long cargoId) {
        return funcionarioRepository.listarPorCargo(cargoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listarPorEquipe(long equipeId) {
        return funcionarioRepository.listarPorEquipe(equipeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listarPorTurno(long turnoId) {
        return funcionarioRepository.listarPorTurno(turnoId);
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionario listarPorCargoIdEFuncionarioId(long cargoId, long funcionarioId) {
        return funcionarioRepository.listarPorCargoIdEFuncionarioId(cargoId, funcionarioId);
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        funcionarioRepository.atualizar(funcionario);
    }

    @Override
    public void excluir(long funcionarioId) {
        funcionarioRepository.excluir(listarPorId(funcionarioId).getFuncionarioId());
    }
}
