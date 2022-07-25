package br.com.qintess.repositories;

import br.com.qintess.entities.Perfil;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PerfilRepository extends CrudRepository<Perfil, Long> {

    /**
     * Busca perfil com nome especificado
     * @param nome Nome
     * @return Perfil
     */
    public Perfil findByNome(String nome);

    /**
     * Busca perfis com nome similiar ao especificado
     * @param nome Nome
     * @return List<Perfil>
     */
    public List<Perfil> findByNomeContainingIgnoreCase(String nome);

}
