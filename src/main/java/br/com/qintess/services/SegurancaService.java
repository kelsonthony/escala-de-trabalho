package br.com.qintess.services;

import br.com.qintess.entities.Usuario;
import br.com.qintess.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("segurancaService")
public class SegurancaService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.listarPorNome(nome);
        if (usuario == null)
            throw new UsernameNotFoundException("Usuário não encontrado!");

        return usuario;
    }
}
