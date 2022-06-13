package br.com.qintess.entities.enumereds;

public enum TipoFeriadoEnum {

  FEDERAL("Federal"),ESTADUAL("Estadual"),MUNICIPAL("Municipal");

  private String valorNome;

  TipoFeriadoEnum(String valorNome) {
    this.valorNome = valorNome;
  }

  public String getValorNome() {
    return valorNome;
  }
}