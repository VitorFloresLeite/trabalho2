package modelo;

public class Horario {
    private Dias dia;
    private Periodos periodo;

    public Horario(Dias dia, Periodos periodo) {
        this.dia = dia;
        this.periodo = periodo;
    }

    public Dias getDia() {
        return dia;
    }

    public void setDia(Dias dia) {
        this.dia = dia;
    }

    public Periodos getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodos periodo) {
        this.periodo = periodo;
    }

    public String descricao() {//Não optei por utilizar void pois a informação não será exibida no console
        return getDia().toString() + " - " + getPeriodo().toString();
    }
}