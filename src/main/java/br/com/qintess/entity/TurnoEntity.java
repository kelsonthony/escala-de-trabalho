package br.com.qintess.entity;


import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;


public class TurnoEntity {

  private UUID id;
  private String descricao;
  private String sigla;
  private LocalTime horarioInicio;
  private LocalTime horarioTermino;
  private LocalTime totalHoras;

  public TurnoEntity(UUID id, String descricao, String sigla, LocalTime horarioInicio, LocalTime horarioTermino, LocalTime totalHoras) {
    this.id = id;
    this.descricao = descricao;
    this.sigla = sigla;
    this.horarioInicio = horarioInicio;
    this.horarioTermino = horarioTermino;
    this.totalHoras = totalHoras;
  }

  public TurnoEntity() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
  }

  public LocalTime getHorarioInicio() {
    return horarioInicio;
  }

  public void setHorarioInicio(LocalTime horarioInicio) {
    this.horarioInicio = horarioInicio;
  }

  public LocalTime getHorarioTermino() {
    return horarioTermino;
  }

  public void setHorarioTermino(LocalTime horarioTermino) {
    this.horarioTermino = horarioTermino;
  }

  public LocalTime getTotalHoras() {
    return totalHoras;
  }

  public void setTotalHoras(LocalTime totalHoras) {
    this.totalHoras = totalHoras;
  }

  @Override
  public String toString() {
    return "TurnoEntity{" +
      "id=" + id +
      ", descricao='" + descricao + '\'' +
      ", sigla='" + sigla + '\'' +
      ", horarioInicio=" + horarioInicio +
      ", horarioTermino=" + horarioTermino +
      ", totalHoras=" + totalHoras +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TurnoEntity that = (TurnoEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(sigla, that.sigla) && Objects.equals(horarioInicio, that.horarioInicio) && Objects.equals(horarioTermino, that.horarioTermino) && Objects.equals(totalHoras, that.totalHoras);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descricao, sigla, horarioInicio, horarioTermino, totalHoras);
  }


}
