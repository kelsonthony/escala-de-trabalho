package br.com.qintess.entities;

import br.com.qintess.entities_old.enumereds.TipoFeriadoEnum;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_TURNO_FIXO")
public class TurnoFixo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Range(min = 0, max = 1)
    private int domingo;

    @Range(min = 0, max = 1)
    private int segunda;

    @Range(min = 0, max = 1)
    private int terca;

    @Range(min = 0, max = 1)
    private int quarta;

    @Range(min = 0, max = 1)
    private int quinta;

    @Range(min = 0, max = 1)
    private int sexta;

    @Range(min = 0, max = 1)
    private int sabado;

    @OneToMany(mappedBy = "turnoFixo", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDomingo() {
        return domingo;
    }

    public void setDomingo(int domingo) {
        this.domingo = domingo;
    }

    public int getSegunda() {
        return segunda;
    }

    public void setSegunda(int segunda) {
        this.segunda = segunda;
    }

    public int getTerca() {
        return terca;
    }

    public void setTerca(int terca) {
        this.terca = terca;
    }

    public int getQuarta() {
        return quarta;
    }

    public void setQuarta(int quarta) {
        this.quarta = quarta;
    }

    public int getQuinta() {
        return quinta;
    }

    public void setQuinta(int quinta) {
        this.quinta = quinta;
    }

    public int getSexta() {
        return sexta;
    }

    public void setSexta(int sexta) {
        this.sexta = sexta;
    }

    public int getSabado() {
        return sabado;
    }

    public void setSabado(int sabado) {
        this.sabado = sabado;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    @Entity(name = "TB_FERIADO")
    public static class FeriadoEntity {

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
}
