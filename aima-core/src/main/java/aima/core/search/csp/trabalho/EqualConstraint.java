package aima.core.search.csp.trabalho;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a binary constraint which forbids equal values.
 * 
 * @author Ruediger Lunde
 */
public class EqualConstraint<VAR extends Variable, VAL> implements Constraint<VAR, VAL> {

	private VAR var1;
	private VAL valFixo;
	private List<VAR> scope;

	public EqualConstraint(VAR var1, VAL valFixo) {
		this.var1 = var1;
		this.valFixo = valFixo;

		scope = new ArrayList<>(1);
		scope.add(var1);

	}

	@Override
	public List<VAR> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<VAR, VAL> assignment) {
		VAL value1 = assignment.getValue(var1);
		return  value1.equals(valFixo);
	}
}
