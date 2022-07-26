package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    void salvar(Usuario usuario);
    List<Usuario> listar();
    Usuario listarPorId(long id);
    Usuario listarPorNome(String nome);
    void atualizar(Usuario usuario);
    void excluir(long id);

}
