package aima.core.search.csp.trabalho;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Cada restrição é definida como uma tuple <scope, rel>, em que scope
 * corresponde a uma tupla de variáveis que participa de uma restrição
 * e rel é uma relação que define os valores que essas variáveis podem assumir.
 *
 * OOs tipos que Constraint recebe são respectivamente, o tipo das variáveis e
 * o tipo dos valores que as variáveis podem assumir.
 */
public class Restricoes implements Constraint<Horario, Disciplina> {
    private Variable variavel1;
    private Variable variavel2;
    private List<Variable> escopo;

    public Restricoes(Variable var1, Variable var2) {
        this.variavel1 = var1;
        this.variavel2 = var2;
        escopo = new ArrayList<>(2);
        escopo.add(var1);
        escopo.add(var2);
    }

    @Override
    public List getScope() {
        return null;
    }

    /**
     * Esta restrição indica que duas variáveis não podem ter os mesmos valores.
     * Ou seja, um horário não pode ser ocupado por mais de uma tarefa ou disciplina.
     *
     * @param assignment objeto que representa a atribuição de valores a uma ou várias variáveis.
     * @return true se a restrição for satisfeita.
     */
    @Override
    public boolean isSatisfiedWith(Assignment assignment) {
        // Adaptado da implementação para o problema da coloração de mapa
        Disciplina valor1 = (Disciplina) assignment.getValue(this.variavel1);
        return valor1 == null || !valor1.equals(assignment.getValue(this.variavel2));
    }
}
