package br.com.qintess.dashboard.entities;

import java.io.Serializable;

public class EspecialidadeDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long cargo;
  private Long turno;
  private int quantidade;

  public EspecialidadeDto(Long cargo, Long turno, int quantidade) {
    this.cargo = cargo;
    this.turno = turno;
    this.quantidade = quantidade;
  }

  public Long getCargo() {
    return cargo;
  }

  public void setCargo(Long cargo) {
    this.cargo = cargo;
  }

  public Long getTurno() {
    return turno;
  }

  public void setTurno(Long turno) {
    this.turno = turno;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }
}
