package aima.core.search.csp.trabalho;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String codigo;
    private final ArrayList<Horario> horarios = new ArrayList<>();

    public Disciplina() {}

    public Disciplina(String codigo) {
        this.codigo = codigo;
    }

    public Disciplina(String codigo, Horario horario) {
        this.codigo = codigo;
        this.horarios.add(horario);
    }

    public Disciplina(String codigo, List<Horario> horarios) {
        this.codigo = codigo;
        this.horarios.addAll(horarios);
    }

    public void adicionarHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public void adicionarHorario(List<Horario> horario) {
        this.horarios.addAll(horario);
    }

    @Override
    public String toString() {
        return "\nDisciplina{" +
                "codigo='" + codigo + '\'' +
                ", horarios=" + horarios +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Horario> getHorario() {
        return horarios;
    }

    public void setHorario(Horario horario) {
        this.horarios.add(horario);
    }
}