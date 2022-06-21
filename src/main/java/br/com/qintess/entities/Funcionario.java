package br.com.qintess.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "TB_FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 8)
    @Column(nullable = false, length = 8)
    private String matricula;

    @NotBlank
    @Size(min = 2, max = 125)
    @Column(nullable = false,length = 125)
    private String nome;

    @Column(nullable = false, length = 1)
    private char localidade;

    @Size(min = 0, max = 14)
    @Column(nullable = true, length = 14)
    private String codigo;

    @Email
    @NotBlank
    @Size(min = 5, max = 255)
    @Column(nullable = false, length = 255)
    private String email;

    @NotBlank
    @Size(min = 5, max = 255)
    @Column(nullable = false, length = 255)
    private String senha;

    @Column(name = "CARGO_FUNCIONARIO_ID")
    private long cargo;

    @Column(name = "EQUIPE_FUNCIONARIO_ID")
    private long equipe;

    @Column(name = "TURNO_ID")
    private long turno;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Escala> escala;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getLocalidade() {
        return localidade;
    }

    public void setLocalidade(char localidade) {
        this.localidade = localidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getCargo() {
        return cargo;
    }

    public void setCargo(long cargo) {
        this.cargo = cargo;
    }

    public long getEquipe() {
        return equipe;
    }

    public void setEquipe(long equipe) {
        this.equipe = equipe;
    }

    public long getTurno() {
        return turno;
    }

    public void setTurno(long turno) {
        this.turno = turno;
    }

    public List<Escala> getEscala() {
        return escala;
    }

    public void setEscala(List<Escala> escala) {
        this.escala = escala;
    }
}
