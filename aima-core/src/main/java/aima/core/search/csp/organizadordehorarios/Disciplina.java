package aima.core.search.csp.organizadordehorarios;

import aima.core.search.csp.Variable;

import java.util.ArrayList;
import java.util.List;

public class Disciplina extends Variable {
    private int numHorarios;
    private final ArrayList<Horario> horarios = new ArrayList<>();
    private int numBlocos;

    public Disciplina(String name) {
        super(name);
    }

    public Disciplina(String codigo, Horario horario) {
        super(codigo);
        this.horarios.add(horario);
        this.numHorarios = this.horarios.size();
    }

    public Disciplina(String codigo, List<Horario> horarios) {
        super(codigo);
        this.horarios.addAll(horarios);
        this.numHorarios = this.horarios.size();
    }

    public void adicionarHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public void adicionarHorario(List<Horario> horario) {
        this.horarios.addAll(horario);
        this.numHorarios = this.horarios.size();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public int getNumBlocos() {
        return numBlocos;
    }

    public void setNumBlocos(int numBlocos) {
        this.numBlocos = numBlocos;
    }
}