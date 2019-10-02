package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;
import org.javatuples.Pair;

public class NeighborSelectorPickBestFromLocalHillImpl implements NeighborSelector {

	private Problem problem;
	private NeighborFactory neighborFactory;
	
	public NeighborSelectorPickBestFromLocalHillImpl(Problem problem, NeighborFactory neighborFactory) {
		this.problem = problem;
		this.neighborFactory = neighborFactory;
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
		
		double currentSol = problem.eval(currentPoint);
		
		// Select a random neighbor.
		ArrayList<Double> currentBestPoint = ProblemUtil.getRandomPoint(problem);
		double currentBest = problem.eval(currentPoint);

		boolean shouldContinue;
		do {
			// Get best neighbor from surrounding neighbors.
			ArrayList<ArrayList<Double>> neighbors = neighborFactory.getNeighbors(problem, currentBestPoint, stepSize);
			Pair<Double, ArrayList<Double>> neighborEvalAndPoint = getBestNeighbor(neighbors);
			
			double neighborSol = neighborEvalAndPoint.getValue0();
			ArrayList<Double> neighborPoint = neighborEvalAndPoint.getValue1();
			
			if (currentBest < neighborSol) {
				currentBest = neighborSol;
				currentBestPoint.set(0, neighborPoint.get(0));
				currentBestPoint.set(1, neighborPoint.get(1));
				shouldContinue = true;
				ProblemNumbers.getInstance().incNoOfNeighborsPicked();
			} else {
				shouldContinue = false;
			}
			
		} while (shouldContinue);
		
		if(currentSol < currentBest) {
			ProblemNumbers.getInstance().incNoOfBetterSolutions();
			result = Pair.with(currentBest, currentBestPoint);
		}
		
		// Can be null.
		return result;
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
			double nSol = problem.eval(point);
			if(neighborSol < nSol) {
				neighborSol = nSol;
				neighborPoint.set(0, point.get(0));
				neighborPoint.set(1, point.get(1));
			}
		}
		
		return Pair.with(neighborSol, neighborPoint);
	}
}
