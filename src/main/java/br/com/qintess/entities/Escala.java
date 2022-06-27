package br.com.qintess.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_ESCALA")
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long escalaId;

    @NotBlank
    @Column(nullable = false)
    private Date data;

    @Size(min = 2, max = 125)
    @Column(nullable = false, length = 125)
    private String nome;

    @OneToMany(mappedBy = "escala")
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "escalaTipo")
    private List<EscalaTipo> tipos;

    public long getEscalaId() {
        return escalaId;
    }

    public void setEscalaId(long escalaId) {
        this.escalaId = escalaId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<EscalaTipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<EscalaTipo> tipos) {
        this.tipos = tipos;
    }
}
