//package dk.eaaa.bm.hillclimber.crap;
//
//import java.util.ArrayList;
//
//import org.javatuples.Pair;
//
//import dk.eaaa.bm.hillclimber.ProblemWrapper;
//
//public class NeighborEvaluatorBestFromNeighborboodImpl {
//
//	private ProblemWrapper problem;
//	
//	public NeighborEvaluatorBestFromNeighborboodImpl(ProblemWrapper problem) {
//		this.problem = problem;
//	}
//	
//	/**
//	 * Given a list of neighbors, return the neighbor with the best evaluation.
//	 */
//	public Pair<Double, ArrayList<Double>> getBestNeighbor(ArrayList<ArrayList<Double>> neighbors) {
//		double neighborSol = -Double.MAX_VALUE;
//		ArrayList<Double> neighborPoint = new ArrayList<>(problem.getProblem().getDimensions());
//		neighborPoint.add(-Double.MAX_VALUE);
//		neighborPoint.add(-Double.MAX_VALUE);
//		
//		for(ArrayList<Double> point : neighbors) {
//			double nSol = problem.evaluate(point);
//			if(neighborSol < nSol) {
//				neighborSol = nSol;
//				neighborPoint.set(0, point.get(0));
//				neighborPoint.set(1, point.get(1));
//			}
//		}
//		
//		return Pair.with(neighborSol, neighborPoint);
//	}
//
//}
