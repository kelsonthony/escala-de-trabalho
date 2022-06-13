package br.com.qintess.services.impl;

import br.com.qintess.entities.FeriadoEntity;
import br.com.qintess.repositories.FeriadoRepository;
import br.com.qintess.services.FeriadoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeriadoServiceImpl implements FeriadoService {

  private final FeriadoRepository feriadoRepository;

  public FeriadoServiceImpl(FeriadoRepository feriadoRepository) {
    this.feriadoRepository = feriadoRepository;
  }

  @Override
  public FeriadoEntity save(final FeriadoEntity feriado) {
    return this.feriadoRepository.save(feriado);
  }

  @Override
  public FeriadoEntity findById(final Integer id) {
    return this.feriadoRepository.findById(id).orElseThrow(()-> new RuntimeException("Não encontrado."));
  }

  @Override
  public List<FeriadoEntity> findAll() {
    return this.feriadoRepository.findAll();
  }

  @Override
  public void deleteById(final Integer id) {
    this.feriadoRepository.deleteById(id);
  }
}
