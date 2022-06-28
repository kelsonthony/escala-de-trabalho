package br.com.qintess.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_ESCALA_TIPO")
public class EscalaTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long escalaTipoId;

    @NotBlank
    @Size(min = 2, max = 125)
    @Column(nullable = false, length = 125)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ESCALA_ID")
    private Escala escala;

    public long getEscalaTipoId() {
        return escalaTipoId;
    }

    public void setEscalaTipoId(long escalaTipoId) {
        this.escalaTipoId = escalaTipoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Escala getEscala() {
        return escala;
    }

    public void setEscala(Escala escala) {
        this.escala = escala;
    }
}
