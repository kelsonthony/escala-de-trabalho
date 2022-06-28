package br.com.qintess.services;

import br.com.qintess.entities.EscalaTipo;
import br.com.qintess.repositories.interfaces.IEscalaTipoRepository;
import br.com.qintess.services.interfaces.IEscalaTipoService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EscalaTipoService implements IEscalaTipoService {

    @Autowired
    private IEscalaTipoRepository escalaTipoRepository;

    @Override
    public void salvar(EscalaTipo escalaTipo) {
        if (escalaTipo.equals(null)) {
            throw new ConstraintViolationException(
                    "Erro ao tentar salvar o tipo de escala (#Objeto vazio).", null, null);
        }
        escalaTipoRepository.salvar(escalaTipo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EscalaTipo> listar() {
        return escalaTipoRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public EscalaTipo listarPorId(final long id) {
        return escalaTipoRepository.listarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EscalaTipo> listarPorEscala(final long id) {
        return escalaTipoRepository.listarPorEscala(id);
    }

    @Override
    public void atualizar(EscalaTipo escalaTipo) {
        escalaTipoRepository.atualizar(escalaTipo);
    }

    @Override
    public void excluir(final long id) {
        EscalaTipo escalaTipo = escalaTipoRepository.listarPorId(id);
        if (escalaTipo.equals(null)) {
            throw new ConstraintViolationException(
                    "Erro ao tentar remover o tipo da escala (#Id não existe).", null, null);
        }
        escalaTipoRepository.excluir(id);
    }
}
