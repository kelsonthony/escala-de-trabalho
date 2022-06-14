package br.com.qintess.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_TURNO_ALTERNADO")
public class TurnoAlternado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Range(min = 0, max = 99)
    @Column(nullable = false)
    private int quantDiasConsecutivosTrabalho;

    @Range(min = 0, max = 99)
    @Column(nullable = false)
    private int quantDiasFolga;

    @OneToMany(mappedBy = "turnoAlternado", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantDiasConsecutivosTrabalho() {
        return quantDiasConsecutivosTrabalho;
    }

    public void setQuantDiasConsecutivosTrabalho(int quantDiasConsecutivosTrabalho) {
        this.quantDiasConsecutivosTrabalho = quantDiasConsecutivosTrabalho;
    }

    public int getQuantDiasFolga() {
        return quantDiasFolga;
    }

    public void setQuantDiasFolga(int quantDiasFolga) {
        this.quantDiasFolga = quantDiasFolga;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
