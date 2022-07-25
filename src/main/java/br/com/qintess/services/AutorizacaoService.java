package br.com.qintess.services;

import br.com.qintess.entities.Autorizacao;
import br.com.qintess.repositories.AutorizacaoRepository;
import br.com.qintess.services.interfaces.IAutorizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("autorizacaoService")
public class AutorizacaoService implements IAutorizacaoService {

    @Autowired
    private AutorizacaoRepository autorizacaoRepository;

    public void setAutorizacaoRepository(AutorizacaoRepository autorizacaoRepository) {
        this.autorizacaoRepository = autorizacaoRepository;
    }

    @Override
    public Autorizacao salvar(Autorizacao autorizacao) {
        return autorizacaoRepository.save(autorizacao);
    }

    @Override
    public void excluir(Long id) {
        autorizacaoRepository.deleteById(id);
    }

    @Override
    public Optional<Autorizacao> listarPorId(Long id) {
        return autorizacaoRepository.findById(id);
    }

    @Override
    public List<Autorizacao> listarPorNome(String nome) {
        return autorizacaoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Autorizacao> listar() {
        Iterable<Autorizacao> autorizacoes = autorizacaoRepository.findAll();
        List<Autorizacao> retorno = new ArrayList<Autorizacao>();
        for(Autorizacao autorizacao : autorizacoes) {
            retorno.add(autorizacao);
        }
        return retorno;
    }

}
