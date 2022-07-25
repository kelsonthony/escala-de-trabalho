package br.com.qintess.services;

import br.com.qintess.entities.Usuario;
import br.com.qintess.repositories.UsuarioRepository;
import br.com.qintess.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        try {
            String encoded = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(encoded);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> listarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> listarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }
}
