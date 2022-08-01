package br.com.qintess.services;

import br.com.qintess.entities.Perfil;
import br.com.qintess.entities.Usuario;
import br.com.qintess.repositories.PerfilRepository;
import br.com.qintess.repositories.UsuarioRepository;
import br.com.qintess.services.interfaces.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("perfilService")
public class PerfilService implements IPerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void setPerfilRepository(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Override
    public Perfil salvar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @Override
    public void excluir(Long id) {

      List<Optional<Usuario>> usuarios = this.usuarioRepository.listaPorIdPerfil(id);

      if(usuarios.isEmpty()){
        perfilRepository.deleteById(id);
      }else {
        throw new RuntimeException("Perfil possui usuários vinculados");
      }
    }

    @Override
    public Optional<Perfil> listarPorId(Long id) {
        return perfilRepository.findById(id);
    }

    @Override
    public List<Perfil> listarPorNome(String nome) {
        return perfilRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Perfil> listar() {
        Iterable<Perfil> perfis = perfilRepository.findAll();
        List<Perfil> retorno = new ArrayList<Perfil>();
        for(Perfil perfil : perfis) {
            retorno.add(perfil);
        }
        return retorno;
    }

}
