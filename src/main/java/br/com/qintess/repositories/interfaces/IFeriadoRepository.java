package br.com.qintess.repositories;

import br.com.qintess.entities.TurnoFixo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeriadoRepository extends JpaRepository<TurnoFixo.FeriadoEntity,Integer> {
}
