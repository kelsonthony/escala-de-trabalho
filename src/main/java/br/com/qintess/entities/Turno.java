package br.com.qintess.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_TURNO")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 125 , message = "Tamanho deve estar entre 2 e 125")
    @Column(nullable = false, length = 125)
    private String nome;

    @NotBlank
    @Column(nullable = true,length = 7, unique = true)
    private String cor;

    @NotBlank
    @Size(min = 1, max = 5 , message = "Tamanho deve estar entre 1 e 5")
    @Column(nullable = false, length = 5)
    private String sigla;

    @NotNull
    @Range(min = 0, max = 1 , message = "Valor deve ser 1 ou 0")
    private int trabalhaNoFeriado;

    @NotBlank
    @Column(nullable = true, length = 8)
    private String horaInicio;

    @NotBlank
    @Column(nullable = true, length = 8)
    private String horaTermino;

    @Column(nullable = true, length = 8)
    private String totalHoras;

    @OneToMany(mappedBy = "turno")
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "turno")
    public List<Dia> dias;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TURNO_FIXO_ID")
    private TurnoFixo turnoFixo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TURNO_ALTERNADO_ID")
    private TurnoAlternado turnoAlternado;

    @Column(name = "padrao_sistema")
    private int padraoSistema;

  public Turno(long id, String nome, String cor, String sigla, int trabalhaNoFeriado, String horaInicio,
               String horaTermino, String totalHoras, List<Funcionario> funcionarios, List<Dia> dias,
               TurnoFixo turnoFixo, TurnoAlternado turnoAlternado, int padraoSistema) {
    this.id = id;
    this.nome = nome;
    this.cor = cor;
    this.sigla = sigla;
    this.trabalhaNoFeriado = trabalhaNoFeriado;
    this.horaInicio = horaInicio;
    this.horaTermino = horaTermino;
    this.totalHoras = totalHoras;
    this.funcionarios = funcionarios;
    this.dias = dias;
    this.turnoFixo = turnoFixo;
    this.turnoAlternado = turnoAlternado;
    this.padraoSistema = padraoSistema;
  }

  public Turno() {
  }

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

  public String getCor() {
    return cor;
  }

  public void setCor(String cor) {
    this.cor = cor;
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

  public List<Dia> getDias() {
    return dias;
  }

  public void setDias(List<Dia> dias) {
    this.dias = dias;
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

  public int getPadraoSistema() {
    return padraoSistema;
  }

  public void setPadraoSistema(int padraoSistema) {
    this.padraoSistema = padraoSistema;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Turno turno = (Turno) o;
    return id == turno.id && trabalhaNoFeriado == turno.trabalhaNoFeriado && padraoSistema == turno.padraoSistema && Objects.equals(nome, turno.nome) && Objects.equals(cor, turno.cor) && Objects.equals(sigla, turno.sigla) && Objects.equals(horaInicio, turno.horaInicio) && Objects.equals(horaTermino, turno.horaTermino) && Objects.equals(totalHoras, turno.totalHoras) && Objects.equals(funcionarios, turno.funcionarios) && Objects.equals(dias, turno.dias) && Objects.equals(turnoFixo, turno.turnoFixo) && Objects.equals(turnoAlternado, turno.turnoAlternado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, cor, sigla, trabalhaNoFeriado, horaInicio, horaTermino, totalHoras, funcionarios, dias, turnoFixo, turnoAlternado, padraoSistema);
  }
}
