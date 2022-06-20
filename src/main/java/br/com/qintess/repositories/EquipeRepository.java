package br.com.qintess.repositories;

import br.com.qintess.entities.Equipe;
import br.com.qintess.repositories.interfaces.IEquipeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EquipeRepository implements IEquipeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Equipe equipe)  { em.persist(equipe); }

    @Override
    public List<Equipe> listar() {
        return em.createQuery("SELECT e FROM Equipe e", Equipe.class).getResultList();
    }

    @Override
    public Equipe listarPorId(long id) {
        return em.find(Equipe.class, id);
    }

    @Override
    public void atualizar(Equipe equipe) {
        em.merge(equipe);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Equipe.class, id));
    }
}
