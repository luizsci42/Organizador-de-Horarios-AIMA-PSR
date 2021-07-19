package aima.core.search.csp.organizadordehorarios;

import java.util.Objects;

public class Horario {
    private String dia;
    private String hora;
    private String nome;

    public Horario(String nome) {
        this.nome = nome;
        this.dia = nome.split("\\S")[0];
        this.hora = nome.split("\\S")[1];
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return Objects.equals(nome, horario.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public String getDia() {
        return this.dia;
    }

    public String getHora() {
        return this.hora;
    }
}
