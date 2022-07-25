package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Perfil;

import java.util.List;
import java.util.Optional;

public interface IPerfilService {

    public Perfil salvar(Perfil perfil);

    public void excluir(Long id);

    public Optional<Perfil> listarPorId(Long id);

    public List<Perfil> listarPorNome(String nome);

    public List<Perfil> listar();

}
