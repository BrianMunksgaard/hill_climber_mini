package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;
import org.javatuples.Pair;

/**
 * Hill climber implementation for solving problems of type Problem.
 */
public class ImprovedHillClimbing {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass().getName());
	
	private final Problem problem;
	private final NeighborFactory neighborFactory;
	
	private int solutionsChecked;
	private int betterSolutions;
	private int betterNeighbors;

	/**
	 * Initialize hill climbing solver.
	 * 
	 * @param problem			The problem defining the search space, constraints and evaluation function.
	 * @param neighborFactory	An implementation of the NeighborFactory interface.
	 */
	public ImprovedHillClimbing(final Problem problem, final NeighborFactory neighborFactory) {
		this.problem = problem;
		this.neighborFactory = neighborFactory;
	}

	/**
	 * Search the optimal point.
	 * 
	 * @param iterations	The number of times to pick a new random point and start climbing.
	 * @param stepSize	The stepSize to use when searching for neighbors.	
	 * @return The solution/optima according to the parameters and the problem definition.
	 */
	public ArrayList<Double> findOptima(int iterations, double stepSize) {
		
		solutionsChecked = 0;
		betterSolutions = 0;
		betterNeighbors = 0;
		
		// Select a random value as a starting point.
		ArrayList<Double> bestPoint = ProblemUtil.getRandomPoint(problem);
		Double bestSol = problem.eval(bestPoint);
				
		for(int i = 0; i < iterations; i++) {
			
			// Select a random neighbor.
			ArrayList<Double> currentPoint = ProblemUtil.getRandomPoint(problem);
			double currentSol = problem.eval(currentPoint);
			
			boolean shouldContinue;
			do {
				// Select surrounding neighbors and pick the best.
				ArrayList<ArrayList<Double>> neighbors = neighborFactory.getNeighbors(problem, currentPoint, stepSize);
				Pair<Double, ArrayList<Double>> bestNeighbor = getBestNeighbor(neighbors);
				
				double neighborSol = bestNeighbor.getValue0();
				ArrayList<Double> neighborPoint = bestNeighbor.getValue1();
				
				// If set current if the neighbor is better and continue climbing.
				if (currentSol < neighborSol) {
					betterNeighbors++;
					currentSol = neighborSol;
					currentPoint.set(0, neighborPoint.get(0));
					currentPoint.set(1, neighborPoint.get(1));
					shouldContinue = true;
				} else {
					shouldContinue = false;
				}
			} while (shouldContinue);
			
			if(bestSol < currentSol) {
				betterSolutions++;
				bestSol = currentSol;
				bestPoint.set(0, currentPoint.get(0));
				bestPoint.set(1, currentPoint.get(1));
			}
		}
		
		String msg = String.format("Best solution is: %.4f, %.4f = %.4f", bestPoint.get(0), bestPoint.get(1), bestSol);
		log.info("Number of solutions checked: {}. Found better neighbor {} times. Found better solution {} times.", solutionsChecked, betterNeighbors, betterSolutions);
		log.info(msg);
		return bestPoint;
	}

	/*
	 * Given a list of neighbors, return the neighbor with the best evaluation.
	 */
	private Pair<Double, ArrayList<Double>> getBestNeighbor(ArrayList<ArrayList<Double>> neighbors) {
		double neighborSol = -Double.MAX_VALUE;
		ArrayList<Double> neighborPoint = new ArrayList<>(problem.getDimensions());
		neighborPoint.add(-Double.MAX_VALUE);
		neighborPoint.add(-Double.MAX_VALUE);
		
		for(ArrayList<Double> point : neighbors) {
			double nSol = evaluate(point);
			if(neighborSol < nSol) {
				neighborSol = nSol;
				neighborPoint.set(0, point.get(0));
				neighborPoint.set(1, point.get(1));
			}
		}
		
		return Pair.with(neighborSol, neighborPoint);
	}

	/*
	 * Wrapper for the eval function in the problem class. Used
	 * for counting the number of calls to the eval function and
	 * log current state.
	 */
	private double evaluate(ArrayList<Double> point) {
		double e = problem.eval(point);
		solutionsChecked++;
		if(log.isTraceEnabled()) {
			String msg = String.format("Solution %d for %.4f, %.4f = %.4f", solutionsChecked, point.get(0), point.get(1), e);
			log.trace(msg);
		}
		return e;
	}
}
