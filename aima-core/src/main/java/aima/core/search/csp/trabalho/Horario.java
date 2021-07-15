package aima.core.search.csp.trabalho;

import aima.core.search.csp.Variable;

public class Horario extends Variable {
    private String dia;
    private String hora;

    public Horario(String dia, String hora) {
        super(dia + " " + hora);
        this.dia = dia;
        this.hora = hora;
    }

    public String getDia() {
        return this.dia;
    }

    public String getHora() {
        return this.hora;
    }

    @Override
    public String toString() {
        return "\nHorario{" +
                "dia='" + dia + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
