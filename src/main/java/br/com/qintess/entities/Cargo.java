package br.com.qintess.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

  @Size(min = 2, max = 125, message = "O tamanho do nome deve ser entre 2 e 125 caracteres")
  @Column(nullable = false, length = 125)
  private String nome;

  @Size(min = 2, max = 10, message = "O tamanho da sigla deve ser entre 2 e 10 caracteres")
  @Column(nullable = false, length = 10)
  private String sigla;

  @Column(name = "carga_horaria_dia", nullable = false)
  private float cargaHorariaDia;

  @Column(name = "carga_horaria_mes")
  private float cargaHorariaMes;

  @NotNull(message = "Digite o número de Horas Extras Permitidas ou 0 para nenhuma")
  @Range(min = 0, max = 999)
  @Column(length = 999)
  private Integer horasExtrasPermitidas;

  @OneToMany(mappedBy = "cargo")
  private List<Funcionario> funcionarios;

  public Cargo() {
  }

  public Cargo(long cargoId, String nome, String sigla, float cargaHorariaDia, float cargaHorariaMes, Integer horasExtrasPermitidas, List<Funcionario> funcionarios) {
    this.cargoId = cargoId;
    this.nome = nome;
    this.sigla = sigla;
    this.cargaHorariaDia = cargaHorariaDia;
    this.cargaHorariaMes = cargaHorariaMes;
    this.horasExtrasPermitidas = horasExtrasPermitidas;
    this.funcionarios = funcionarios;
  }

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

  public float getCargaHorariaDia() {
    return cargaHorariaDia;
  }

  public void setCargaHorariaDia(float cargaHorariaDia) {
    this.cargaHorariaDia = cargaHorariaDia;
  }

  public float getCargaHorariaMes() {
    return cargaHorariaMes;
  }

  public void setCargaHorariaMes(float cargaHorariaMes) {
    this.cargaHorariaMes = cargaHorariaMes;
  }

  public Integer getHorasExtrasPermitidas() {
    return horasExtrasPermitidas;
  }

  public void setHorasExtrasPermitidas(Integer horasExtrasPermitidas) {
    this.horasExtrasPermitidas = horasExtrasPermitidas;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }


}
