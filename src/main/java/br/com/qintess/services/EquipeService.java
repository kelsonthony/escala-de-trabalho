package br.com.qintess.services;

import br.com.qintess.entities.Equipe;
import br.com.qintess.repositories.interfaces.IEquipeRepository;
import br.com.qintess.services.interfaces.IEquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipeService implements IEquipeService {

    @Autowired
    private IEquipeRepository equipeRepository;

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
        equipeRepository.excluir(id);
    }
}
