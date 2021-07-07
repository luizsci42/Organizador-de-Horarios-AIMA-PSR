package aima.gui.demo.search;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.BidirectionalEightPuzzleProblem;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.informed.RecursiveBestFirstSearch;
import aima.core.search.uninformed.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * @author Luiz Felipe Souza
 */

public class EightPuzzleDemo {
	public static void main(String[] args) {
		// System.out.println("Initial State:\n" + boardWithThreeMoveSolution);

		System.out.println("Executando agentes de busca cega");
		executarAgentesDeBuscaCega();
		System.out.println("Executando agentes de heurística");
		executarAgentesDeBuscaInformada();
	}

	private static Properties resolver(
			SearchForActions<EightPuzzleBoard, Action> agenteBusca,
			EightPuzzleBoard tabuleiro) {
		try {
			// Cria uma instância do ambiente
			Problem<EightPuzzleBoard, Action> problema = new BidirectionalEightPuzzleProblem(tabuleiro);
			// Cria uma instância do agente
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problema, agenteBusca);
			return agent.getInstrumentation();
		} catch (Exception e) {
			System.out.println("Opa!");
			e.printStackTrace();
		}
		return null;
	}

	public static void executarAgentesDeBuscaInformada() {
		GenerateRandomEightPuzzleDemo geradorEstadosIniciais = new GenerateRandomEightPuzzleDemo();

		SearchForActions<EightPuzzleBoard, Action> gula = new GreedyBestFirstSearch<>
				(new GraphSearch<>(), EightPuzzleFunctions::getNumberOfMisplacedTiles);
		SearchForActions<EightPuzzleBoard, Action> aStar = new AStarSearch<>
				(new GraphSearch<>(), EightPuzzleFunctions::getNumberOfMisplacedTiles);
		SearchForActions<EightPuzzleBoard, Action> recursiveBestFirstSearch = new RecursiveBestFirstSearch<>
				(AStarSearch.createEvalFn(EightPuzzleFunctions::getNumberOfMisplacedTiles), true);

		List<SearchForActions<EightPuzzleBoard, Action>> agentesBuscaHeuristica = new ArrayList<>();
		agentesBuscaHeuristica.add(gula);
		agentesBuscaHeuristica.add(aStar);
		agentesBuscaHeuristica.add(recursiveBestFirstSearch);

		agirEmDiferentesEstados(agentesBuscaHeuristica);
	}

	public static void executarAgentesDeBuscaCega() {
		// Instanciamos os agentes de busca
		SearchForActions<EightPuzzleBoard, Action> breadthFirstSearch = new BreadthFirstSearch<>(new GraphSearch<>());
		SearchForActions<EightPuzzleBoard, Action> uniformCostSearch = new UniformCostSearch<>(new GraphSearch<>());
		SearchForActions<EightPuzzleBoard, Action> depthFirstSearch = new DepthFirstSearch<>(new GraphSearch<>());
		SearchForActions<EightPuzzleBoard, Action> depthLimitedSearch = new DepthLimitedSearch<>(15);
		SearchForActions<EightPuzzleBoard, Action> iterativeDeepeningSearch = new IterativeDeepeningSearch<>();

		// Fazemos uma lista com os agentes
		List<SearchForActions<EightPuzzleBoard, Action>> agentesBuscaCega = new ArrayList<>();
		agentesBuscaCega.add(breadthFirstSearch);
		agentesBuscaCega.add(uniformCostSearch);
		agentesBuscaCega.add(depthFirstSearch);
		agentesBuscaCega.add(depthLimitedSearch);
		agentesBuscaCega.add(iterativeDeepeningSearch);

		agirEmDiferentesEstados(agentesBuscaCega);
	}

	private static void agirEmDiferentesEstados(List<SearchForActions<EightPuzzleBoard, Action>> listaAgentes) {
		GenerateRandomEightPuzzleDemo geradorEstadosIniciais = new GenerateRandomEightPuzzleDemo();

		EightPuzzleBoard[] estadosIniciais = new EightPuzzleBoard[32];

		for(int i = 0; i < 32; i++) {
			EightPuzzleBoard novoEstadoInicial = geradorEstadosIniciais.gerarTabuleiroValido();
			estadosIniciais[i] = novoEstadoInicial;
		}

		for(SearchForActions<EightPuzzleBoard, Action> agente : listaAgentes) {
			double nosExpandidos = 0, custoCaminho = 0, tamanhoMaximoFila = 0, tamanhoFila = 0;

			for (int i = 0; i < 32; i++) {
				System.out.println("Estado Inicial:\n" + estadosIniciais[i]);
				Properties instrumentation = resolver(agente, estadosIniciais[i]);
				try {
					try {
						assert instrumentation != null;
						nosExpandidos += Double.parseDouble(instrumentation.getProperty("nodesExpanded"));
					} catch (NullPointerException e) {
						// System.out.println("Nós expandidos: NaN");
						continue;
					}
					try {
						custoCaminho += Double.parseDouble(instrumentation.getProperty("pathCost"));
					} catch (NullPointerException e) {
						// System.out.println("Custo do caminho: NaN");
						continue;
					}
					try {
						tamanhoMaximoFila += Double.parseDouble(instrumentation.getProperty("maxQueueSize"));
					} catch (NullPointerException e) {
						// System.out.println("Tamanho máximo da fila: NaN");
						continue;
					}
					try {
						tamanhoFila += Double.parseDouble(instrumentation.getProperty("queueSize"));
					} catch (NullPointerException e) {
						// System.out.println("Tamanho da fila: NaN");
					}
				} catch (OutOfMemoryError e) {
					System.out.println("Out of memory");
				}
			}

			double mediaNosExpandidos = nosExpandidos / 32;
			double mediaCustoCaminho = custoCaminho / 32;
			double mediaTamanhoMaxFila = tamanhoMaximoFila / 32;
			double mediaTamanhoFila = tamanhoFila / 32;

			System.out.println("\nMédia de nós expandidos: " + Double.toString(mediaNosExpandidos));
			System.out.println("Media de custo do caminho: " + Double.toString(mediaCustoCaminho));
			System.out.println("Média do tamanho máximo da fila: " + Double.toString(mediaTamanhoMaxFila));
			System.out.println("Média do tamanho da fila: " + Double.toString(mediaTamanhoFila));
		}
	}
}