package br.com.qintess.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PERFIL")
public class Perfil implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4127301875156085916L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long perfilId;

    @Column(name = "NOME", unique=true, length = 35, nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "TB_AUTORIZACAO_PERFIL",
            joinColumns = { @JoinColumn(name = "PERFIL_ID") },
            inverseJoinColumns = { @JoinColumn(name = "AUTORIZACAO_ID") })
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();

    public Long getId() {
        return perfilId;
    }

    public void setId(Long id) {
        this.perfilId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(List<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }
}
