public class Horario {
    enum DiaSemana {
        SEGUNDA, TERÇA, QUARTA, QUINTA, SEXTA
    }
    enum Turno{
        manha_turno1, manha_turno2, tarde_turno1, tarde_turno2, noite_turno1, noite_turno2
    }
    private DiaSemana dia;
    private Turno hora;
    private Professor Docente;
    Disciplina disciplina;

    public Horario(DiaSemana dia, Turno hora, Professor docente, Disciplina disciplina) {
        this.dia = dia;
        this.hora = hora;
        Docente = docente;
        this.disciplina = disciplina;

    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}