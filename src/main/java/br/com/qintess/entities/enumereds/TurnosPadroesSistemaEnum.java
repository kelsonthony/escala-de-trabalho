package br.com.qintess.entities.enumereds;

public enum TurnosPadroesSistemaEnum {

  FERIADO("FR"),FOLGA("FG"),FERIAS("FE"),LICENCA("LC");

  private String sigla;

  TurnosPadroesSistemaEnum(String sigla) {
    this.sigla = sigla;
  }

  public String getSigla() {
    return sigla;
  }

}
