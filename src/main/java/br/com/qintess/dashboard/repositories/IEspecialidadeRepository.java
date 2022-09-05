package br.com.qintess.dashboard.repositories;

import br.com.qintess.dashboard.entities.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspecialidadeRepository extends JpaRepository<Especialidade,Long> {
}
