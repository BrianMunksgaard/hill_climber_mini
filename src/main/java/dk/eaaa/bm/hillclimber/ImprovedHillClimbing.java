package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;
import java.util.List;

public class ImprovedHillClimbing {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass().getName());
	
	private final List<Double> universe;
	
	private final Problem problem;

	/**
	 * Initialize hill climbing solver.
	 * 
	 * @param universe	The search space.
	 * @param problem	The problem defining the constraints and evaluation function.
	 */
	public ImprovedHillClimbing(final List<Double> universe, final Problem problem) {
		this.universe = universe;
		this.problem = problem;
	}

	public ArrayList<Double> findOptima(int iterations) {
		
		// Select a random value as a starting point.
		ArrayList<Double> currentSolution = getRandomPoint(problem);
		
		for(int i = 0; i < iterations; i++) {
			
			// Select a random neighbor. SHOULD GET FROM UNIVERSE
			ArrayList<Double> alternativeSolution = getRandomPoint(problem);
			
		
			// Compare the current solution with the alternative solution.
			double valCurrentSolution = problem.eval(currentSolution);
			double valAlternativeSolution = problem.eval(alternativeSolution);
			log.info("Comparing current solution of {} to alternative (neighbor) solution of {}.", valCurrentSolution, valAlternativeSolution);
			
			if (valCurrentSolution < valAlternativeSolution) {
				log.info("Chosing alternative solution.");
				currentSolution = alternativeSolution;
			} 

		}
		//return bestSolution;
		return null;
	}
	
	/*
	 * Generates a random starting point for the specified problem.
	 */
	private ArrayList<Double> getRandomPoint(Problem problem) {
		
		ArrayList<Double> randomPoint = new ArrayList<>(problem.getDimensions());
		
		// Generate random value for each dimension in the point.
		for(int dim = 0; dim < problem.getDimensions(); dim++) {
			
			// Get lower and upper bounds for the current dimension.
			Double minValCurrentDim = problem.getMinValues().get(dim);
			Double maxValCurrentDim = problem.getMaxValues().get(dim);
			
			// Generate random value between lower and upper bounds.
			Double randomDimValue = minValCurrentDim + Math.random() * (maxValCurrentDim - minValCurrentDim);
			randomPoint.add(randomDimValue);
		}
		
		return randomPoint;
	}

	public static void main(String[] args) {

//		ArrayList<Double> list = new ArrayList<>();
//
//		// TODO add to list
//
//		ImprovedHillClimbing test = new ImprovedHillClimbing(list);
//		System.out.println(test.findOptima());
	}
	
	

}
