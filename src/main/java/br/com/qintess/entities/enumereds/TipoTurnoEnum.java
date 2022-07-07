package br.com.qintess.entities.enumereds;

public enum TipoTurnoEnum {

  FIXO("Fixo"), ALTERNADO("Alternado");

  private String nome;

  TipoTurnoEnum(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

}
