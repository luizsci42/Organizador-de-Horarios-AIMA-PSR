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
public class StudyConstraint<VAR extends Variable, VAL> implements Constraint<VAR, VAL> {

	private VAR var;
	private VAL val;
	private List<VAR> scope;

	public StudyConstraint(VAR var, VAL val) {
		this.var = var;

		scope = new ArrayList<>(1);
		scope.add(var);

	}

	@Override
	public List<VAR> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<VAR, VAL> assignment) {
		long count;
		var value  = assignment.getValue(var);
		if(value instanceof StudyTIme){
//			for (VAR v: assignment.getVariables()) {
//				VAL va = assignment.getValue(v);
//				if(val instanceof StudyTIme && va instanceof StudyTIme && ((StudyTIme) va).disciplina.getCodigo() == ((StudyTIme) val).disciplina.getCodigo()){
//					count++;
//				}
//			}

			count =  assignment.getVariables().stream().filter(var1 -> value.equals(assignment.getValue(var1))).count();
			return count <= ((StudyTIme) value).numberBlock;
		}
		return true;

	}
}
