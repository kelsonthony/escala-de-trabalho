package br.com.qintess.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_SUPORTE")
public class Suporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O RE não pode estar em branco.")
    @Size(min = 8, max = 8, message = "O RE deve ter 8 caracteres e seguir o padrão.")
    @Column(nullable = false, length = 8)
    private String re;

    @NotBlank(message = "O e-mail não pode estar em branco.")
    @Size(min = 5, max = 250, message = "O e-mail deve ter entre 5 e 250 caracteres.")
    @Email
    @Column(nullable = false, length = 250)
    private String email;

    @NotBlank
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
