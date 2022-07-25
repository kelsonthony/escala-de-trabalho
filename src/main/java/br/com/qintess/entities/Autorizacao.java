package br.com.qintess.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "TB_AUTORIZACAO")
public class Autorizacao implements GrantedAuthority {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2084366362705923518L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long autorizacaoId;

    @Column(name = "NOME", unique=true, length = 35, nullable = false)
    private String nome;

    @Override
    public String getAuthority() {
        return this.nome;
    }

    public Long getId() {
        return autorizacaoId;
    }

    public void setId(Long id) {
        this.autorizacaoId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
