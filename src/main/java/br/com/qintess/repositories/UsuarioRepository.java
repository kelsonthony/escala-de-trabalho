package br.com.qintess.repositories;

import br.com.qintess.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    /**
     * Busca usuario com nome especificado
     * @param nome Nome
     * @return Usuario
     */
    public Usuario findByNome(String nome);

    /**
     * Busca usuarios com nome similiar ao especificado
     * @param nome Nome
     * @return List<Usuario>
     */
    public List<Usuario> findByNomeContainingIgnoreCase(String nome);

}
