package br.com.qintess.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DashboardDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long funcionarioId;
    private final String funcionario;
    private final String cargo;
    private final String cargoSigla;
    private final List<Dia> dias;

    public DashboardDto(Mes mes) {
        this.funcionarioId = mes.getFuncionario().getFuncionarioId();
        this.funcionario = mes.getFuncionario().getNome();
        this.cargo = mes.getFuncionario().getCargo().getNome();
        this.cargoSigla = mes.getFuncionario().getCargo().getSigla();
        this.dias = mes.getDias();
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public String getCargo() {
        return cargo;
    }

    public String getCargoSigla() {
        return cargoSigla;
    }

    public List<Dia> getDias() {
        return dias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardDto entity = (DashboardDto) o;
        return Objects.equals(this.funcionarioId, entity.funcionarioId) &&
                Objects.equals(this.funcionario, entity.funcionario) &&
                Objects.equals(this.cargo, entity.cargo) &&
                Objects.equals(this.cargoSigla, entity.cargoSigla) &&
                Objects.equals(this.dias, entity.dias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcionarioId, funcionario, cargo, cargoSigla, dias);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "funcionarioId = " + funcionarioId + ", " +
                "funcionario = " + funcionario + ", " +
                "cargo = " + cargo + ", " +
                "cargoSigla = " + cargoSigla + ")" +
                "dias = " + dias + ")";
    }
}
