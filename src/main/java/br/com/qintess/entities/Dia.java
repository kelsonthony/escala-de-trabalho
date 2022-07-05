package br.com.qintess.entities;

import javax.persistence.*;

@Entity
@Table(name = "TB_DIA")
public class Dia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "dia_do_mes")
  private int diaDoMes;

  @ManyToOne
  @JoinColumn(name = "mes_id")
  private Mes mes;

  @ManyToOne
  @JoinColumn(name = "turno_id")
  private Turno turno;

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

  public Mes getMes() {
    return mes;
  }

  public void setMes(Mes mes) {
    this.mes = mes;
  }

  public Turno getTurno() {
    return turno;
  }

  public void setTurno(Turno turno) {
    this.turno = turno;
  }
}
