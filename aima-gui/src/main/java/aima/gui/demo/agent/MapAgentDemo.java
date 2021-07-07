package aima.gui.demo.agent;

import aima.core.agent.Agent;
import aima.core.agent.EnvironmentListener;
import aima.core.agent.impl.DynamicPercept;
import aima.core.agent.impl.SimpleEnvironmentView;
import aima.core.environment.map.*;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.uninformed.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates how different kinds of search algorithms perform in a route finding scenario.
 * @author Ruediger Lunde
 */
public class MapAgentDemo {
	public static void main(String[] args) {
		ExtendableMap map = new ExtendableMap();
		SimplifiedRoadMapOfRomania.initMap(map);
		MapEnvironment env = new MapEnvironment(map);
		EnvironmentListener<Object, Object> envView = new SimpleEnvironmentView();
		env.addEnvironmentListener(envView);

		String agentLoc = SimplifiedRoadMapOfRomania.ARAD;
		String destination = SimplifiedRoadMapOfRomania.BUCHAREST;

		String[] locIniciais = {
				agentLoc,
				SimplifiedRoadMapOfRomania.BUCHAREST,
				SimplifiedRoadMapOfRomania.CRAIOVA,
				SimplifiedRoadMapOfRomania.DOBRETA,
				SimplifiedRoadMapOfRomania.EFORIE,
				SimplifiedRoadMapOfRomania.FAGARAS,
				SimplifiedRoadMapOfRomania.GIURGIU,
				SimplifiedRoadMapOfRomania.HIRSOVA,
				SimplifiedRoadMapOfRomania.IASI,
				SimplifiedRoadMapOfRomania.RIMNICU_VILCEA,
				SimplifiedRoadMapOfRomania.VASLUI,
				SimplifiedRoadMapOfRomania.URZICENI,
				SimplifiedRoadMapOfRomania.MEHADIA,
				SimplifiedRoadMapOfRomania.PITESTI,
				SimplifiedRoadMapOfRomania.LUGOJ,
				SimplifiedRoadMapOfRomania.ZERIND,
				SimplifiedRoadMapOfAustralia.ADELAIDE,
				SimplifiedRoadMapOfAustralia.ALBANY,
				SimplifiedRoadMapOfAustralia.ALICE_SPRINGS,
				SimplifiedRoadMapOfAustralia.BRISBANE,
				SimplifiedRoadMapOfAustralia.BROKEN_HILL,
				SimplifiedRoadMapOfAustralia.BROOME,
				SimplifiedRoadMapOfAustralia.CAIRNS,
				SimplifiedRoadMapOfAustralia.CAMARVON,
				SimplifiedRoadMapOfAustralia.CANBERRA,
				SimplifiedRoadMapOfAustralia.CHARLEVILLE,
				SimplifiedRoadMapOfAustralia.COOBER_PEDY,
				SimplifiedRoadMapOfAustralia.DARWIN,
				SimplifiedRoadMapOfAustralia.DUBBO,
				SimplifiedRoadMapOfAustralia.ESPERANCE,
				SimplifiedRoadMapOfAustralia.GERALDTON,
				SimplifiedRoadMapOfAustralia.HALLS_CREEK,
				SimplifiedRoadMapOfAustralia.HAY
		};

		// SearchForActions<String, MoveToAction> search;


		SearchForActions<String, MoveToAction> breadthFirstSearch = new BreadthFirstSearch<>(new GraphSearch<>());
		SearchForActions<String, MoveToAction> uniformCostSearch = new UniformCostSearch<>(new TreeSearch<>());
		SearchForActions<String, MoveToAction> depthFirstSearch = new DepthFirstSearch<>(new GraphSearch<>());
		SearchForActions<String, MoveToAction> depthLimitedSearch = new DepthLimitedSearch<>(15);
		SearchForActions<String, MoveToAction> iterativeDeepeningSearch = new IterativeDeepeningSearch<>();
		// search = new UniformCostSearch<>(new GraphSearch<>());
		// SearchForActions<String, MoveToAction> search = new AStarSearch<>(new GraphSearch<>(), MapFunctions.createSLDHeuristicFunction(destination, map));

		List<SearchForActions> agentesBusca = new ArrayList<>();
		// agentesBusca.add(breadthFirstSearch);
		// agentesBusca.add(uniformCostSearch);
		// agentesBusca.add(depthFirstSearch);
		// agentesBusca.add(depthLimitedSearch);
			agentesBusca.add(iterativeDeepeningSearch);

		// Iteramos pela lista de agentes e executamos cada um no ambiente
		for (SearchForActions<String, MoveToAction> agenteBusca : agentesBusca) {
			Agent<DynamicPercept, MoveToAction> agent;
			// agent = new SimpleMapAgent(map, agenteBusca, destination);
			agent = new MapAgent(map, agenteBusca, destination);

			double nosExpandidos = 0, custoCaminho = 0, tamanhoMaximoFila = 0, tamanhoFila = 0;

			for(int i = 0; i < 32; i++) {
				agent = new MapAgent(map, agenteBusca, destination);
				// Adicionamos o agente no ambiente
				env.addAgent(agent, locIniciais[i]);
				// Executamos as ações
				env.stepUntilDone();

				nosExpandidos += agenteBusca.getMetrics().getDouble("nodesExpanded");
				custoCaminho += agenteBusca.getMetrics().getDouble("pathCost");
				tamanhoMaximoFila += agenteBusca.getMetrics().getDouble("maxQueueSize");
				tamanhoFila += agenteBusca.getMetrics().getDouble("queueSize");

				env.removeAgent(agent);
			}

			double mediaNosExpandidos = nosExpandidos / 32;
			double mediaCustoCaminho = custoCaminho / 32;
			double mediaTamanhoMaxFila = tamanhoMaximoFila / 32;
			double mediaTamanhoFila = tamanhoFila / 32;

			System.out.println("Média de nós expandidos: " + Double.toString(mediaNosExpandidos));
			System.out.println("Media de custo do caminho: " + Double.toString(mediaCustoCaminho));
			System.out.println("Média do tamanho máximo da fila: " + Double.toString(mediaTamanhoMaxFila));
			System.out.println("Média do tamanho da fila: " + Double.toString(mediaTamanhoFila));
			// envView.notify(agenteBusca.getMetrics().toString());
			env.removeAgent(agent);
		}
	}
}
