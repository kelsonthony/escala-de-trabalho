package br.com.qintess.repositories;

import br.com.qintess.entities.Autorizacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutorizacaoRepository extends CrudRepository<Autorizacao, Long> {

    /**
     * Busca autorizacao com nome especificado
     * @param nome Nome
     * @return Autorizacao
     */
    public Autorizacao findByNome(String nome);

    /**
     * Busca autorizacoes com nome similiar ao especificado
     * @param nome Nome
     * @return List<Autorizacao>
     */
    public List<Autorizacao> findByNomeContainingIgnoreCase(String nome);

}
