package br.com.qintess.dashboard.entities;

import br.com.qintess.entities.Escala;
import br.com.qintess.entities.Funcionario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_dashboard")
public class Dashboard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 125, unique = true)
  private String nome;

  @OneToMany
  @JoinColumn(name = "id")
  private List<Especialidade> especialidades = new ArrayList<>();

  @ManyToOne
  private Escala escala;

  public Dashboard() {
  }

  public void addFuncionarioEspecialidade(){

    List<Funcionario> funcionarios = this.escala.getFuncionarios();

    for (Funcionario funcionario: funcionarios) {

      for (Especialidade e: this.especialidades) {

        if(e.getTurno() == funcionario.getTurno() && e.getCargo() == funcionario.getCargo() && e.getFuncionario() == null){
          e.setFuncionario(funcionario);
          break;
        }

      }

    }

  }

  public List<Especialidade> getEspecialidades() {
    return especialidades;
  }

  public void setEspecialidades(List<Especialidade> especialidades) {
    this.especialidades = especialidades;
  }

  public Escala getEscala() {
    return escala;
  }

  public void setEscala(Escala escala) {
    this.escala = escala;
  }

}
