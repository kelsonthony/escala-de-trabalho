package br.com.qintess.repositories;

import br.com.qintess.entities.Usuario;
import br.com.qintess.repositories.interfaces.IUsuarioRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
//
//    /**
//     * Busca usuario com nome especificado
//     * @param nome Nome
//     * @return Usuario
//     */
////    public Usuario findByNome(String nome);
//
//
//    /**
//     * Busca usuarios com nome similiar ao especificado
//     * @param nome Nome
//     * @return List<Usuario>
//     */
//    public List<Usuario> findByNomeContainingIgnoreCase(String nome);
//
//}

@Repository
public class UsuarioRepository implements IUsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    @Override
    public Usuario listarPorId(long id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public Usuario listarPorNome(String login) {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public void atualizar(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Usuario.class, id));
    }
}