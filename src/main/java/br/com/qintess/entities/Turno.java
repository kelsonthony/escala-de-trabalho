package br.com.qintess.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "TB_TURNO")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 125 )
    @Column(nullable = false, length = 125)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 5)
    @Column(nullable = false, length = 5)
    private String sigla;

    @NotNull
    @Range(min = 0, max = 1)
    private int trabalhaNoFeriado;

    @NotBlank
    @Column(nullable = true, length = 8)
    private String horaInicio;

    @NotBlank
    @Column(nullable = true, length = 8)
    private String horaTermino;

    @NotBlank
    @Column(nullable = true, length = 8)
    private String totalHoras;

    @OneToMany(mappedBy = "turno", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TURNO_FIXO_ID")
    private TurnoFixo turnoFixo;

    @ManyToOne
    @JoinColumn(name = "TURNO_ALTERNADO_ID")
    private TurnoAlternado turnoAlternado;

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

    public int getTrabalhaNoFeriado() {
        return trabalhaNoFeriado;
    }

    public void setTrabalhaNoFeriado(int trabalhaNoFeriado) {
        this.trabalhaNoFeriado = trabalhaNoFeriado;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(String totalHoras) {
        this.totalHoras = totalHoras;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public TurnoFixo getTurnoFixo() {
        return turnoFixo;
    }

    public void setTurnoFixo(TurnoFixo turnoFixo) {
        this.turnoFixo = turnoFixo;
    }

    public TurnoAlternado getTurnoAlternado() {
        return turnoAlternado;
    }

    public void setTurnoAlternado(TurnoAlternado turnoAlternado) {
        this.turnoAlternado = turnoAlternado;
    }
}
