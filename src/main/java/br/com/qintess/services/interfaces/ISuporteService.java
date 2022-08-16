package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Suporte;
import java.util.List;

public interface ISuporteService {

    void salvar(Suporte suporte);
    List<Suporte> listar();

}
