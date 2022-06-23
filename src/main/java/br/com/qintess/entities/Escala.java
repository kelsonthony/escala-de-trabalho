package br.com.qintess.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "TB_ESCALA_TRABALHO")
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long escalaId;

    @NotBlank
    @Column(nullable = false)
    private Date data;

    @Size(min = 0, max = 9)
    @Column(nullable = true, length = 9)
    private String totalHoras;

    @Size(min = 0, max = 9)
    @Column(nullable = true, length = 9)
    private String totalHorasExtras;

    @Range(min = 0, max = 99)
    @Column(nullable = true)
    private int quantSabadosTrabalhados;

    @Range(min = 0, max = 99)
    @Column(nullable = true)
    private int quantDomingosTrabalhados;

    @Range(min = 0, max = 99)
    @Column(nullable = true)
    private int quantFeriadosTrabalhados;

    @ManyToOne
    @JoinColumn(name = "FUNCIONARIO_ID")
    private Funcionario funcionario;

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

    public String getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(String totalHoras) {
        this.totalHoras = totalHoras;
    }

    public String getTotalHorasExtras() {
        return totalHorasExtras;
    }

    public void setTotalHorasExtras(String totalHorasExtras) {
        this.totalHorasExtras = totalHorasExtras;
    }

    public int getQuantSabadosTrabalhados() {
        return quantSabadosTrabalhados;
    }

    public void setQuantSabadosTrabalhados(int quantSabadosTrabalhados) {
        this.quantSabadosTrabalhados = quantSabadosTrabalhados;
    }

    public int getQuantDomingosTrabalhados() {
        return quantDomingosTrabalhados;
    }

    public void setQuantDomingosTrabalhados(int quantDomingosTrabalhados) {
        this.quantDomingosTrabalhados = quantDomingosTrabalhados;
    }

    public int getQuantFeriadosTrabalhados() {
        return quantFeriadosTrabalhados;
    }

    public void setQuantFeriadosTrabalhados(int quantFeriadosTrabalhados) {
        this.quantFeriadosTrabalhados = quantFeriadosTrabalhados;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
