package br.com.qintess.entities;


import java.time.LocalTime;
import java.util.UUID;


public class CargoEntity {

  private UUID id;
  private String nome;
  private String sigla;
  private LocalTime horasExtrasPermitidas;

  public CargoEntity(UUID id, String nome, String sigla, LocalTime horasExtrasPermitidas) {
    this.id = id;
    this.nome = nome;
    this.sigla = sigla;
    this.horasExtrasPermitidas = horasExtrasPermitidas;
  }

  public CargoEntity() {
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

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
  }

  public LocalTime getHorasExtrasPermitidas() {
    return horasExtrasPermitidas;
  }

  public void setHorasExtrasPermitidas(LocalTime horasExtrasPermitidas) {
    this.horasExtrasPermitidas = horasExtrasPermitidas;
  }
}
