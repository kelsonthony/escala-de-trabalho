package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Autorizacao;

import java.util.List;
import java.util.Optional;

public interface IAutorizacaoService {

    public Autorizacao salvar(Autorizacao autorizacao);

    public void excluir(Long id);

    public Optional<Autorizacao> listarPorId(Long id);

    public List<Autorizacao> listarPorNome(String nome);

    public List<Autorizacao> listar();

}
