package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.TurnoFixo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeriadoRepository extends JpaRepository<TurnoFixo.FeriadoEntity,Integer> {
}
