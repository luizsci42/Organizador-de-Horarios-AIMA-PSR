package aima.core.search.csp.trabalho;

import aima.core.search.csp.Variable;

public class Horario extends Variable {
    private String dia;
    private String hora;

    public Horario(String name) {
        super(name);
        this.dia = name.split("\\S")[0];
        this.hora = name.split("\\S")[1];
    }

    public String getDia() {
        return this.dia;
    }

    public String getHora() {
        return this.getName().split("\\S")[1];
    }
}
