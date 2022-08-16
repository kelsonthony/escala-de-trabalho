package br.com.qintess.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_SUPORTE")
public class Suporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false, length = 6)
    private String re;

    @NotBlank
    @Size(min = 5, max = 255, message = "Tamanho deve estar entre 5 e 255")
    @Email
    @Column(nullable = false, length = 255)
    private String email;

    @NotBlank
    @Size(min = 5, max = 25, message = "Tamanho deve estar entre 5 e 25")
    @Column(nullable = false, length = 25)
    private String solicitacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }
}
