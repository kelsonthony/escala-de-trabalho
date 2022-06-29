package br.com.qintess.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_ESCALA")
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long escalaId;

    @NotNull(message = "Não pode esta em branco")
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    @NotNull
    @Size(min = 2, max = 125, message = "Tamanho deve estar entre 2 e 125")
    @Column(nullable = false, length = 125)
    private String nome;

    @OneToMany(mappedBy = "escala")
    private List<EscalaTipo> tipos;

    @ManyToMany
    @JoinTable(name = "TB_ESCALA_FUNCIONARIO",
            joinColumns = @JoinColumn(name = "escala_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private List<Funcionario> funcionarios;

    public long getEscalaId() {
        return escalaId;
    }

    public void setEscalaId(long escalaId) {
        this.escalaId = escalaId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<EscalaTipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<EscalaTipo> tipos) {
        this.tipos = tipos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
