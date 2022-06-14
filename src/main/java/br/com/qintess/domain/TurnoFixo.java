package br.com.qintess.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;

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
}
