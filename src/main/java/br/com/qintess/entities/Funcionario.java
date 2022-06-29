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
    @Column(name = "ID")
    private long funcionarioId;

    @NotBlank
    @Size(min = 2, max = 8, message = "Tamanho deve estar entre 2 e 8")
    @Column(nullable = false, length = 8)
    private String matricula;

    @NotBlank
    @Size(min = 2, max = 125 , message = "Tamanho deve estar entre 2 e 125")
    @Column(nullable = false, length = 125)
    private String nome;

    @Column(nullable = false, length = 1)
    private char localidade;

    @Size(min = 2, max = 14, message = "Tamanho deve estar entre 2 e 14")
    @Column(nullable = false, length = 14)
    private String codigo;

    @NotBlank
    @Size(min = 5, max = 255, message = "Tamanho deve estar entre 5 e 255")
    @Email
    @Column(nullable = false, length = 255)
    private String email;

    @NotBlank
    @Size(min = 5, max = 255, message = "Tamanho deve estar entre 6 e 255")
    @Column(nullable = false, length = 255)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "CARGO_ID")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "EQUIPE_ID")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "TURNO_ID")
    private Turno turno;

    @ManyToMany(mappedBy = "funcionarios")
    private List<Escala> escalas;

    public long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(long funcionarioId) {
        this.funcionarioId = funcionarioId;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public List<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<Escala> escalas) {
        this.escalas = escalas;
    }

}
