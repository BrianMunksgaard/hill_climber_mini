package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;
import org.javatuples.Pair;

/**
 * Hill climber implementation for solving problems of type Problem.
 */
public class ImprovedHillClimbing {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass().getName());
	
	private final Problem problem;
//	private final NeighborFactory neighborFactory;
	private final NeighborSelector neighborSelector;
	

	/**
	 * Initialize hill climbing solver.
	 * 
	 * @param problem			The problem defining the search space, constraints and evaluation function.
	 * @param neighborSelector	An implementation of the NeighborSelector interface.
	 */
	public ImprovedHillClimbing(final Problem problem, final NeighborSelector neighborSelector) {
		this.problem = problem;
		this.neighborSelector = neighborSelector;
	}
	
	/**
	 * Search the optimal point.
	 * 
	 * @param iterations	The number of times to pick a new point.
	 * @param stepSize	The stepSize to use when searching for neighbors.	
	 * @return The solution/optima according to the parameters and the problem definition.
	 */
	public ArrayList<Double> findOptima(int iterations, double stepSize) {
		
		ProblemNumbers.getInstance().reset();
		
		// Select a random value as a starting point.
		ArrayList<Double> bestPoint = ProblemUtil.getRandomPoint(problem);
		Double bestSol = problem.eval(bestPoint);
				
		for(int i = 0; i < iterations; i++) {
			// Pick a neighbor.
			Pair<Double, ArrayList<Double>> newPoint = neighborSelector.pickNeighbor(bestPoint, stepSize);
			if(newPoint != null) {
				bestSol = newPoint.getValue0();
				bestPoint = newPoint.getValue1();
			}
		}
		
		String msg = String.format("Best solution is: %.4f, %.4f = %.4f", bestPoint.get(0), bestPoint.get(1), bestSol);
		log.info("Number of solutions checked: {}. Picked better neighbor {} times. Found better solution {} times.", ProblemNumbers.getInstance().getNoOfSolutionsChecked(), ProblemNumbers.getInstance().getNoOfNeighborsPicked(), ProblemNumbers.getInstance().getNoOfBetterSolutions());
		log.info(msg);
		return bestPoint;
	}
}
