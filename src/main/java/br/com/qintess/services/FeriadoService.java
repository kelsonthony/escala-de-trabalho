package br.com.qintess.services;

import br.com.qintess.entities.FeriadoEntity;

import java.util.List;

public interface FeriadoService {

  FeriadoEntity save(FeriadoEntity feriado);
  FeriadoEntity findById(Integer id);
  List<FeriadoEntity> findAll();
  void deleteById(Integer id);

}
