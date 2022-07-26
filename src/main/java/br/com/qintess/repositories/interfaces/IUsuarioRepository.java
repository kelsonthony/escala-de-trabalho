package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Usuario;

import java.util.List;

public interface IUsuarioRepository {

    void salvar(Usuario usuario);
    List<Usuario> listar();
    Usuario listarPorId(long id);
    Usuario listarPorNome(String login);
    void atualizar(Usuario usuario);
    void excluir(long id);

}
