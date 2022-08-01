package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {

    void salvar(Usuario usuario);
    List<Usuario> listar();
    Usuario listarPorId(long id);
    List<Optional<Usuario>>  listaPorIdPerfil(Long idPerfil);
    Usuario listarPorNome(String login);
    void atualizar(Usuario usuario);
    void excluir(long id);

}
