package br.com.qintess.services;

import br.com.qintess.entities.Equipe;
import br.com.qintess.repositories.interfaces.IEquipeRepository;
import br.com.qintess.repositories.interfaces.IFuncionarioRepository;
import br.com.qintess.services.interfaces.IEquipeService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipeService implements IEquipeService {

    @Autowired
    private IEquipeRepository equipeRepository;

    @Autowired
    IFuncionarioRepository funcionarioRepository;

    @Override
    public void salvar(Equipe equipe) {
        equipeRepository.salvar(equipe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipe> listar() {
        return equipeRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Equipe listarPorId(long id) {
        return equipeRepository.listarPorId(id);
    }

    @Override
    public void atualizar(Equipe cargo) {
        equipeRepository.atualizar(cargo);
    }

    @Override
    public void excluir(long id) {
        boolean temFuncionarios = funcionarioRepository.temFuncionarios("equipe", id);
        if (temFuncionarios){
            throw new ConstraintViolationException(
                    "Não é possível excluir uma Equipe que possui funcionários cadastrados.", null, null);
        }

        equipeRepository.excluir(id);
    }
}
