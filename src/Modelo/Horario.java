package Modelo;

import java.util.Objects;

public class Horario {
    private Dias dia;
    private Turnos turno;
    private Periodos periodo;

    public Horario(Dias dia, Turnos turno, Periodos periodo) {
        this.dia = dia;
        this.turno = turno;
        this.periodo = periodo;
    }

    public Dias getDia() {
        return dia;
    }

    public void setDia(Dias dia) {
        this.dia = dia;
    }

    public Turnos getTurno() {
        return turno;
    }

    public void setTurno(Turnos turno) {
        this.turno = turno;
    }

    public Periodos getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodos periodo) {
        this.periodo = periodo;
    }
    @Override
    public String toString() {//Não optei por utilizar void pois a informação não será exibida no console
        return getDia().toString() + " - " + getTurno().toString() + " - " + getPeriodo().toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Horario horario = (Horario) obj;
        return dia == horario.dia && turno == horario.turno && periodo == horario.periodo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, turno, periodo);
    }

}