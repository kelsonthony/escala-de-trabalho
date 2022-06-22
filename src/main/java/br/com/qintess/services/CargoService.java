package br.com.qintess.services;

import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.repositories.interfaces.ICargoRepository;
import br.com.qintess.repositories.interfaces.IFuncionarioRepository;
import br.com.qintess.services.interfaces.ICargoService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CargoService implements ICargoService {

    @Autowired
    private ICargoRepository cargoRepository;

    @Autowired
    IFuncionarioRepository funcionarioRepository;

    @Override
    public void salvar(Cargo cargo) {
        cargoRepository.salvar(cargo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> listar() {
        return cargoRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo listarPorId(long id) {
        return cargoRepository.listarPorId(id);
    }

    @Override
    public void atualizar(Cargo cargo) {
        cargoRepository.atualizar(cargo);
    }

    @Override
    public void excluir(long id) {
        boolean temFuncionarios = funcionarioRepository.temFuncionarios("cargo", id);
        if (temFuncionarios){
            throw new ConstraintViolationException(
                    "Não é possível excluir um cargo que possui funcionários cadastrados.", null, null);
        }
        cargoRepository.excluir(id);
    }
}
