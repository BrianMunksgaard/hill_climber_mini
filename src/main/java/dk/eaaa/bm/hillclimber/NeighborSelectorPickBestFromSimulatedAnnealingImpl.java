package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;
import java.util.Random;

import org.javatuples.Pair;

public class NeighborSelectorPickBestFromSimulatedAnnealingImpl implements NeighborSelector {

	private Problem problem;
	private NeighborFactory neighborFactory;
	private double coolingRate;
	private double temperature;

	public NeighborSelectorPickBestFromSimulatedAnnealingImpl(Problem problem, NeighborFactory neighborFactory, double temperature, double coolingRate) {
		this.problem = problem;
		this.neighborFactory = neighborFactory;
		this.temperature = temperature;
		this.coolingRate = coolingRate;
	}

	/**
	 * Pick a new neighbor from the neighborhood.
	 * 
	 * @param currentPoint		The current best point.
	 * @param stepSize			Step size to use when generating neighbors.
	 * @return					A pair containing an evaluation of the the new best point and the best point itself.
	 */
	@Override
	public Pair<Double, ArrayList<Double>> pickNeighbor(ArrayList<Double> currentPoint, double stepSize) {

		Pair<Double, ArrayList<Double>> result = null;
		
		// Get random neighbor from close neighborhood.
		ArrayList<ArrayList<Double>> neighbors = neighborFactory.getNeighbors(problem, currentPoint, stepSize);
		ArrayList<Double> neighborPoint = neighbors.get(getRandomIndex(neighbors.size()));
		
		// Get evaluations.
		double pointEval = problem.eval(currentPoint);
		double neighborPointEval = problem.eval(neighborPoint);
		
		// Select the point.
		if(pointEval < neighborPointEval) {
			result = Pair.with(neighborPointEval, neighborPoint);
			ProblemNumbers.getInstance().incNoOfBetterSolutions();
		} else {
			if( temperature > coolingRate) {
				double probability = Math.exp((neighborPointEval - pointEval) / temperature );
				if (Math.random() < probability) {
					ProblemNumbers.getInstance().incNoOfNeighborsPicked();
					result = Pair.with(neighborPointEval, neighborPoint);
				}
			}
		}
		
		// Adjust temperature.
		temperature *= 1 - coolingRate;

		// Can be null.
		return result;
	}

	private int getRandomIndex(int max) {
		final Random random = new Random();
		return random.nextInt(max);
	}
}
