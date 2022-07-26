package br.com.qintess.repositories;

import br.com.qintess.entities.Usuario;
import br.com.qintess.repositories.interfaces.IUsuarioRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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