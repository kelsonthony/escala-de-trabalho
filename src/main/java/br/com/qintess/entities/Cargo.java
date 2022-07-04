package br.com.qintess.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_CARGO")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long cargoId;

    @Size(min = 2, max = 125, message = "Tamanho deve estar entre 2 e 125")
    @Column(nullable = false, length = 125)
    private String nome;

    @Size(min = 2, max = 10, message = "Tamanho deve estar entre 2 e 10")
    @Column(nullable = false, length = 10)
    private String sigla;

    @Size(min = 2, max = 10, message = "Tamanho deve estar entre 2 e 10")
    @Column(nullable = false, length = 10)
    private String permissaoAcesso;

    @NotBlank
    @Range(min = 0, max = 999)
    @Column(nullable = true, length = 999)
    private int horasExtrasPermitidas = 0;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    public long getCargoId() {
        return cargoId;
    }

    public void setCargoId(long cargoId) {
        this.cargoId = cargoId;
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
