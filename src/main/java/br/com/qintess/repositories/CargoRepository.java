package br.com.qintess.repositories;

import br.com.qintess.repositories.interfaces.ICargoRepository;
import br.com.qintess.entities.Cargo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CargoRepository implements ICargoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Cargo cargo) {
        em.persist(cargo);
    }

    @Override
    public List<Cargo> listar() {
        return em.createQuery("SELECT c FROM Cargo c", Cargo.class).getResultList();
    }

    @Override
    public Cargo listarPorId(long id) {
        return em.find(Cargo.class, id);
    }

    @Override
    public void atualizar(Cargo cargo) {
        em.merge(cargo);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Cargo.class, id));
    }
}
