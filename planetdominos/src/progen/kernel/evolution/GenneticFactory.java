package progen.kernel.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import progen.context.ProGenContext;
import progen.kernel.error.Warning;

/**
 * Fábrica encargada de cargar todos los operadores genéticos disponibles para
 * cada ejecución. También es la encargada de proporcionarlos, una vez cargados,
 * para que se puedan utilizar en las distintas etapas de la evolución del
 * programa.
 * 
 * @author jirsis
 * 
 */
public class GenneticFactory {
    /** Lista en la que se almacenan todos los operadores. */
    private List<GenneticOperator> operators;

    /**
     * Constructor de la clase, encargado de instanciar todos los operadores
     * genéticos disponibles para la ejecución de este experimento.
     */
    public GenneticFactory() {
	operators = new ArrayList<GenneticOperator>();
	checkProbability();
	loadOperators();
    }

    /**
     * Comprueba, si la suma de probabilidades de todos los operadores de ese
     * tipo suman 100%.
     * 
     * @return <code>true</code> si la suma es 100%
     */
    private boolean checkProbability() {
	int totalOperators = Integer.parseInt(ProGenContext
		.getMandatoryProperty("progen.total.operators"));
	double probability = 0.0;
	for (int i = 0; i < totalOperators; i++) {
	    probability += ProGenContext.getOptionalPercent(
		    "progen.gennetic.operator" + i + ".probability",
		    (1 - probability) + "");
	}

	if (probability != 1.0) {
	    Warning.show(2);
	    throw new BadConfigurationGenneticOperatorsException(probability);
	}
	return true;
    }

    /**
     * Especifica e instancia el conjunto total de operadores genéticos
     * disponibles en la ejecución.
     */
    private void loadOperators() {
	int totalOperators = Integer.parseInt(ProGenContext
		.getMandatoryProperty("progen.total.operators"));
	GenneticOperator operator;
	String operatorClass;
	String selectorPropertyContext;
	double probability = 0.0;
	String selectorName;
	Map<String, String> selectorParams;

	for (int idOperator = 0; idOperator < totalOperators; idOperator++) {
	    selectorPropertyContext="progen.gennetic.operator"+idOperator+".selector";
	    try {
		operatorClass = ProGenContext
			.getMandatoryProperty("progen.gennetic.operator"
				+ idOperator + ".class");
		operator = (GenneticOperator) Class.forName(
			"progen.kernel.evolution.operators." + operatorClass)
			.newInstance();

		probability = ProGenContext.getOptionalPercent(
			"progen.gennetic.operator" + idOperator
				+ ".probability", (1 - probability) + "");
		operator.setProbability(probability);

		selectorName = ProGenContext.getMandatoryProperty(selectorPropertyContext);
		selectorParams = ProGenContext.getParameters(selectorPropertyContext);

		operator.setSelector(selectorName, selectorParams);

		operators.add(operator);
	    } catch (ClassNotFoundException e) {
		throw new UndefinedGenneticOperatorException(e.getMessage());
	    } catch (Exception e) {
		throw new GenneticOperatorException(e.getMessage());
	    }
	}
    }

    /**
     * Devuelve un operador genético de la lista de los posibles.
     * 
     * @return El operador a ejecutar.
     */
    public GenneticOperator getOperator() {
	double probability = Math.random();
	double actualProb = 0.0;
	int idOperator = 0;
	while (actualProb <= probability) {
	    actualProb += operators.get(idOperator++).getProbability();
	}
	return operators.get(--idOperator);
    }
}
