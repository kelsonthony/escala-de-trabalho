package br.com.qintess.entities;


import br.com.qintess.entities.enumereds.TipoFeriadoEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "TB_FERIADO")
public class FeriadoEntity {

  @Id
  private int id;
  private String nome;
  private LocalDate data;
  private TipoFeriadoEnum tipo;
  @Column(name = "dia_semana")
  private String diaDaSemana;


  public FeriadoEntity(int id, String nome, LocalDate data,TipoFeriadoEnum tipo, String diaDaSemana) {
    this.id = id;
    this.nome = nome;
    this.data = data;
    this.tipo = tipo;
    this.diaDaSemana = diaDaSemana;

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

  public TipoFeriadoEnum getTipo() {
    return tipo;
  }

  public void setTipo(TipoFeriadoEnum tipo) {
    this.tipo = tipo;
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
      ", tipo=" + tipo +
      ", diaDaSemana='" + diaDaSemana + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FeriadoEntity that = (FeriadoEntity) o;
    return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(data, that.data) && tipo == that.tipo && Objects.equals(diaDaSemana, that.diaDaSemana);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, data, tipo, diaDaSemana);
  }
}
