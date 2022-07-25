package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public Usuario salvar(Usuario usuario);

    public void excluir(Long id);

    public Optional<Usuario> listarPorId(Long id);

    public List<Usuario> listarPorNome(String nome);

}
