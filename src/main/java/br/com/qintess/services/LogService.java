package br.com.qintess.services;

import br.com.qintess.entities.Log;
import br.com.qintess.repositories.interfaces.ILogRepository;
import br.com.qintess.services.interfaces.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogService implements ILogService {

  @Autowired
  private ILogRepository logRepository;

  @Override
  public void salvar(Log log) {
     this.logRepository.salvar(log);
  }

  @Override
  public List<Log> listar() {
    return this.logRepository.listar();
  }
}
