package br.com.qintess.entity;


import java.time.LocalDate;
import java.util.Objects;

public class FeriadoEntity {

  private int id;
  private String nome;
  private LocalDate data;
  private String diaDaSemana;
  private int valorDia;

  public FeriadoEntity(int id, String nome, LocalDate data, String diaDaSemana, int valorDia) {
    this.id = id;
    this.nome = nome;
    this.data = data;
    this.diaDaSemana = diaDaSemana;
    this.valorDia = valorDia;
  }

  public FeriadoEntity() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public int getValorDia() {
    return valorDia;
  }

  public void setValorDia(int valorDia) {
    this.valorDia = valorDia;
  }

  @Override
  public String toString() {
    return "FeriadoEntity{" +
      "id=" + id +
      ", nome='" + nome + '\'' +
      ", data=" + data +
      ", diaDaSemana='" + diaDaSemana + '\'' +
      ", valorDia=" + valorDia +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FeriadoEntity that = (FeriadoEntity) o;
    return valorDia == that.valorDia && Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(data, that.data) && Objects.equals(diaDaSemana, that.diaDaSemana);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, data, diaDaSemana, valorDia);
  }
}
