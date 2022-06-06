package br.com.qintess.entity;


import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


public class FeriadoEntity {

  private UUID id;
  private String nome;
  private LocalDate data;
  private String diaDaSemana;

  public FeriadoEntity(UUID id, String nome, LocalDate data, String diaDaSemana) {
    this.id = id;
    this.nome = nome;
    this.data = data;
    this.diaDaSemana = diaDaSemana;
  }

  public FeriadoEntity() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public String getDiaDaSemana() {
    return diaDaSemana;
  }

  public void setDiaDaSemana(String diaDaSemana) {
    this.diaDaSemana = diaDaSemana;
  }

  @Override
  public String toString() {
    return "FeriadoEntity{" +
      "id=" + id +
      ", nome='" + nome + '\'' +
      ", data=" + data +
      ", diaDaSemana='" + diaDaSemana + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FeriadoEntity that = (FeriadoEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(data, that.data) && Objects.equals(diaDaSemana, that.diaDaSemana);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, data, diaDaSemana);
  }
}
