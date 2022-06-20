package br.com.qintess.repositories.interfaces;

import br.com.qintess.entities.Equipe;

import java.util.List;

public interface IEquipeRepository {

    void salvar(Equipe equipe);
    List<Equipe> listar();
    Equipe listarPorId(long id);
    void atualizar(Equipe equipe);
    void excluir(long id);
}
