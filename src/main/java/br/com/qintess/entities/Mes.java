package br.com.qintess.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "TB_MES")
public class Mes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "numero_mes",length = 2, nullable = false)
  private int numeroMes;

  @Column(name = "data_inicio")
  private LocalDate dataInicio;

  @Column(name = "data_termino")
  private LocalDate dataTermino;

  @Column(name = "total_horas_normais")
  private Timestamp totalHorasNormais;

  @Column(name = "total_horas_extras")
  private Timestamp totalHorasExtras;

  @Column(name = "sabados_trabalhados")
  private int sabadosTrabalhados;

  @Column(name = "domingos_trabalhados")
  private int domingosTrabalhados;

  @Column(name = "feriados_trabalhados")
  private int feriadosTrabalhados;

  @ManyToOne
  @JoinColumn(name = "escala_id")
  private Escala escala;

  @ManyToOne
  @JoinColumn(name = "funcionario_id")
  private Funcionario funcionario;

  @OneToMany(mappedBy = "mes",cascade = CascadeType.ALL)
  private List<Dia> dias;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getNumeroMes() {
    return numeroMes;
  }

  public void setNumeroMes(int numeroMes) {
    this.numeroMes = numeroMes;
  }

  public LocalDate getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
  }

  public LocalDate getDataTermino() {
    return dataTermino;
  }

  public void setDataTermino(LocalDate dataTermino) {
    this.dataTermino = dataTermino;
  }

  public Timestamp getTotalHorasNormais() {
    return totalHorasNormais;
  }

  public void setTotalHorasNormais(Timestamp totalHorasNormais) {
    this.totalHorasNormais = totalHorasNormais;
  }

  public Timestamp getTotalHorasExtras() {
    return totalHorasExtras;
  }

  public void setTotalHorasExtras(Timestamp totalHorasExtras) {
    this.totalHorasExtras = totalHorasExtras;
  }

  public int getSabadosTrabalhados() {
    return sabadosTrabalhados;
  }

  public void setSabadosTrabalhados(int sabadosTrabalhados) {
    this.sabadosTrabalhados = sabadosTrabalhados;
  }

  public int getDomingosTrabalhados() {
    return domingosTrabalhados;
  }

  public void setDomingosTrabalhados(int domingosTrabalhados) {
    this.domingosTrabalhados = domingosTrabalhados;
  }

  public int getFeriadosTrabalhados() {
    return feriadosTrabalhados;
  }

  public void setFeriadosTrabalhados(int feriadosTrabalhados) {
    this.feriadosTrabalhados = feriadosTrabalhados;
  }

  public Escala getEscala() {
    return escala;
  }

  public void setEscala(Escala escala) {
    this.escala = escala;
  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }

  public List<Dia> getDias() {
    return dias;
  }

  public void setDias(List<Dia> dias) {
    this.dias = dias;
  }
}
