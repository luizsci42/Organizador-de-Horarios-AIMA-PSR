package aima.core.search.csp.trabalho;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;

import java.util.List;

public class Restricoes implements Constraint<Horario, Disciplina> {
    @Override
    public List getScope() {
        return null;
    }

    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        return false;
    }
}
