package br.com.qintess.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "TB_EQUIPE_FUNCIONARIO")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long equipeId;

    @NotBlank
    @Size(min = 2, max = 125, message = "Tamanho deve estar entre 2 e 125")
    @Column(nullable = false, length = 125)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 4000,message = "Tamanho deve estar entre 2 e 4000")
    @Column(nullable = false, length = 4000)
    private String descricao;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    public long getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(long equipeId) {
        this.equipeId = equipeId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
