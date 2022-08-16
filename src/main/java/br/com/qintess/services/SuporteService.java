package br.com.qintess.services;

import br.com.qintess.entities.Suporte;
import br.com.qintess.repositories.interfaces.ISuporteRepository;
import br.com.qintess.services.interfaces.ISuporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SuporteService implements ISuporteService {

    @Autowired
    private ISuporteRepository suporteRepository;

    @Override
    public void salvar(Suporte suporte) {
        this.suporteRepository.salvar(suporte);
    }

    @Override
    public List<Suporte> listar() {
        return this.suporteRepository.listar();
    }
}
