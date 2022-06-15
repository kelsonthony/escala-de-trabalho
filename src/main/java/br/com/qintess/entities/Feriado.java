package br.com.qintess.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "TB_FERIADO")
public class Feriado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 125)
    @Column(nullable = false, length = 125)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private Date data;

    @NotBlank
    @Size(min = 2, max = 8)
    @Column(nullable = false, length = 8)
    private String tipo;

    @NotBlank
    @Size(min = 2, max = 13)
    @Column(name = "dia_semana", nullable = false, length = 13)
    private String diaSemana;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
}