package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;
import java.util.Arrays;

import org.javatuples.Pair;

public class ImprovedHillClimbing {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass().getName());
	
	private final Problem problem;
	private final NeighborFactory neighborFactory;
	private final double stepSize;
	
	private int solutionsChecked;

	/**
	 * Initialize hill climbing solver.
	 * 
	 * @param problem	The problem defining the search space, constraints and evaluation function.
	 */
	public ImprovedHillClimbing(final Problem problem, final NeighborFactory neighborFactory, double stepSize) {
		this.problem = problem;
		this.neighborFactory = neighborFactory;
		this.stepSize = stepSize;
	}

	public ArrayList<Double> findOptima(int iterations) {
		
		solutionsChecked = 0;
		
		// Select a random value as a starting point.
		ArrayList<Double> bestPoint = ProblemUtil.getRandomPoint(problem);
		Double bestSol = problem.eval(bestPoint);
		
//		ArrayList<Double> bestPoint = new ArrayList<>(Arrays.asList(Double.MIN_VALUE, Double.MIN_VALUE));
//		Double bestSol = Double.MIN_VALUE;
		
		for(int i = 0; i < iterations; i++) {
			
			// Select a random neighbor.
			ArrayList<Double> currentPoint = ProblemUtil.getRandomPoint(problem);
			double currentSol = problem.eval(currentPoint);
			
			boolean shouldContinue;
			do {
				ArrayList<ArrayList<Double>> neighbors = neighborFactory.getNeighbors(problem, currentPoint, stepSize);
				Pair<Double, ArrayList<Double>> bestNeighbor = getBestNeighbor(neighbors);
				
				double neighborSol = bestNeighbor.getValue0();
				ArrayList<Double> neighborPoint = bestNeighbor.getValue1();
				
				if (currentSol < neighborSol) {
					currentSol = neighborSol;
					currentPoint.set(0, neighborPoint.get(0));
					currentPoint.set(1, neighborPoint.get(1));
					shouldContinue = true;
				} else {
					shouldContinue = false;
				}
			} while (shouldContinue);
			
			if(bestSol < currentSol) {
				bestSol = currentSol;
				bestPoint.set(0, currentPoint.get(0));
				bestPoint.set(1, currentPoint.get(1));
			}
		}
		
		String msg = String.format("Best solution is: %.4f, %.4f = %.4f", bestPoint.get(0), bestPoint.get(1), bestSol);
		log.info("Number of solutions checked: {}.", solutionsChecked);
		log.info(msg);
		return bestPoint;
	}

	/*
	 * Given a list of neighbors, return the neighbor with the best evaluation.
	 */
	private Pair<Double, ArrayList<Double>> getBestNeighbor(ArrayList<ArrayList<Double>> neighbors) {
//		double neighborSol = Double.MIN_VALUE;
//		ArrayList<Double> neighborPoint = new ArrayList<>(problem.getDimensions());
//		neighborPoint.add(Double.MIN_VALUE);
//		neighborPoint.add(Double.MIN_VALUE);
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
