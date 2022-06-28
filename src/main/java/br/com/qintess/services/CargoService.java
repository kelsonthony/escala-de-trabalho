package br.com.qintess.services;

import br.com.qintess.entities.Cargo;
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
    private IFuncionarioRepository funcionarioRepository;

    @Override
    public void salvar(Cargo cargo) {
        if (cargo.equals(null)) {
            throw new ConstraintViolationException(
                    "Erro ao tentar salvar o cargo (#Objeto vazio).", null, null);
        }
        cargoRepository.salvar(cargo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> listar() {
        return cargoRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo listarPorId(final long id) {
        return cargoRepository.listarPorId(id);
    }

    @Override
    public void atualizar(Cargo cargo) {
        cargoRepository.atualizar(cargo);
    }

    @Override
    public void excluir(final long id) {
        Cargo cargo = cargoRepository.listarPorId(id);
        boolean temFuncionarios = funcionarioRepository.temFuncionarios("cargo", id);

        if (cargo.equals(null))
            throw new ConstraintViolationException("Erro ao tentar remover o cargo (#Id não existe).", null, null);

        if (temFuncionarios){
            throw new ConstraintViolationException(
                    "Não é possível remover um cargo que possui funcionários cadastrados.", null, null);
        }

        cargoRepository.excluir(id);
    }
}
