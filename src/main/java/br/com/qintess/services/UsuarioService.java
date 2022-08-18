package br.com.qintess.services;

import br.com.qintess.entities.Usuario;
import br.com.qintess.repositories.UsuarioRepository;
import br.com.qintess.services.interfaces.IUsuarioService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("usuarioService")
@Transactional
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void salvar(Usuario usuario) {
        try {
            if (usuario.equals(null)) {
                throw new ConstraintViolationException(
                        "Erro ao tentar salvar o usuario (#Objeto vazio).", null, null);
            }
            String encoded = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(encoded);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        usuarioRepository.salvar(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario listarPorId(final long id) {
        return usuarioRepository.listarPorId(id);
    }

    @Override
    public Usuario listarPorNome(String nome){
        return usuarioRepository.listarPorNome(nome);
    }

    @Override
    public void atualizar(Usuario usuario) {
        usuarioRepository.atualizar(usuario);
    }

    @Override
    public void excluir(final long id) {
        Usuario usuario = usuarioRepository.listarPorId(id);
        if (usuario.equals(null))
            throw new ConstraintViolationException("Erro ao tentar remover o usuário (#Id não existe).", null, null);

        usuarioRepository.excluir(id);
    }

}
