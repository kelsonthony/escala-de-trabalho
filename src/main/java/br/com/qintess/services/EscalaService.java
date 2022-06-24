package br.com.qintess.services;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.repositories.interfaces.IEscalaRepository;
import br.com.qintess.services.interfaces.IEscalaService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EscalaService implements IEscalaService {

    @Autowired
    private IEscalaRepository escalaRepository;

    @Override
    public void salvar(Escala escala) {
        if (escala.equals(null)) {
            throw new ConstraintViolationException(
                    "Erro ao tentar salvar a escala (#Objeto vazio).", null, null);
        }
        escalaRepository.salvar(escala);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Escala> listar() {
        return escalaRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Escala listarPorId(final long id) {
        return escalaRepository.listarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Escala> listarPorFuncionario(final long funcionarioId) {
        return escalaRepository.listarPorFuncionario(funcionarioId);
    }

    @Override
    public void atualizar(Escala escala) {
        escalaRepository.atualizar(escala);
    }

    @Override
    public void excluir(final long id) {
        Escala escala = escalaRepository.listarPorId(id);
        if (escala.equals(null)) {
            throw new ConstraintViolationException("Erro ao tentar remover a escala (#Id não existe).", null, null);
        }
        escalaRepository.excluir(id);
    }

}
