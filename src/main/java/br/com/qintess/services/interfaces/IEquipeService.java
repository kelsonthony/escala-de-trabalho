package br.com.qintess.services.interfaces;

import br.com.qintess.entities.Equipe;

import java.util.List;

public interface IEquipeService {

    void salvar(Equipe equipe);
    List<Equipe> listar();
    Equipe listarPorId(long id);
    void atualizar(Equipe cargo);
    void excluir(long id);

}
