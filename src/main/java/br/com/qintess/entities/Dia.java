package br.com.qintess.entities;

import javax.persistence.*;

@Entity(name = "TB_DIA")
public class Dia {

  @Id
  private long id;

  @Column(name = "dia_do_mes")
  private int diaDoMes;

  @ManyToOne
  @JoinColumn(name = "mes_id")
  private Mes mesId;

  @ManyToOne
  @JoinColumn(name = "turno_id")
  private Turno turnoId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getDiaDoMes() {
    return diaDoMes;
  }

  public void setDiaDoMes(int diaDoMes) {
    this.diaDoMes = diaDoMes;
  }

  public Mes getMesId() {
    return mesId;
  }

  public void setMesId(Mes mesId) {
    this.mesId = mesId;
  }

  public Turno getTurnoId() {
    return turnoId;
  }

  public void setTurnoId(Turno turnoId) {
    this.turnoId = turnoId;
  }
}
