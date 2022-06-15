package br.com.qintess.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "TB_CARGO_FUNCIONARIO")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 125)
    @Column(nullable = false, length = 125)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(nullable = false, length = 10)
    private String sigla;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(nullable = false, length = 10)
    private String permissaoAcesso;

    @Range(min = 0, max = 10)
    @Column(nullable = true, length = 999)
    private int horasExtrasPermitidas;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getPermissaoAcesso() {
        return permissaoAcesso;
    }

    public void setPermissaoAcesso(String permissaoAcesso) {
        this.permissaoAcesso = permissaoAcesso;
    }

    public int getHorasExtrasPermitidas() {
        return horasExtrasPermitidas;
    }

    public void setHorasExtrasPermitidas(int horasExtrasPermitidas) {
        this.horasExtrasPermitidas = horasExtrasPermitidas;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
