package br.com.qintess.dashboard.entities;

import br.com.qintess.entities.Cargo;
import br.com.qintess.entities.Funcionario;
import br.com.qintess.entities.Turno;

import javax.persistence.*;

@Entity(name = "tb_especialidade")
public class Especialidade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Cargo cargo;

  @ManyToOne
  private Turno turno;

  @ManyToOne
  private Funcionario funcionario;

  public Especialidade() {
  }

  public Especialidade(Cargo cargo, Turno turno, Funcionario funcionario) {
    this.cargo = cargo;
    this.turno = turno;
    this.funcionario = funcionario;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public Turno getTurno() {
    return turno;
  }

  public void setTurno(Turno turno) {
    this.turno = turno;
  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }

  @Override
  public String toString() {
    return "Especialidade{" +
      "cargo=" + cargo +
      ", turno=" + turno +
      ", funcionario=" + funcionario +
      '}';
  }
}
