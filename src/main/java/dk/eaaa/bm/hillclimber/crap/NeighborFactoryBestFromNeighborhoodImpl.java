//package dk.eaaa.bm.hillclimber.crap;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//import org.javatuples.Pair;
//
//import dk.eaaa.bm.hillclimber.NeighborFactory;
//import dk.eaaa.bm.hillclimber.Problem;
//
///**
// * Generates a neighborhood using some neighborFactory implementation
// * and picks a random neighbor from that neighborhood.
// */
//public class NeighborFactoryBestFromNeighborhoodImpl implements NeighborFactory {
//
//	Problem problem;
//	NeighborFactory neighborFactory;
//	
//	public NeighborFactoryBestFromNeighborhoodImpl(Problem problem, NeighborFactory neighborFactory) {
//		this.neighborFactory = neighborFactory;
//	}
//	
//	/**
//	 * Picks best neighbor from the neighborhood and returns it.
//	 */
//	@Override
//	public ArrayList<ArrayList<Double>> getNeighbors(Problem problem, ArrayList<Double> point, double stepSize) {
//
//		ArrayList<ArrayList<Double>> neighbors = neighborFactory.getNeighbors(problem, point, stepSize);
//		ArrayList<Double> bestNeighbor = getBestNeighbor(neighbors);
//		
//		ArrayList<ArrayList<Double>> result = new ArrayList<>();
//		result.add(bestNeighbor);
//		
//		return result; 
//	}
//
//	/*
//	 * Given a list of neighbors, return the neighbor with the best evaluation.
//	 */
//	private ArrayList<Double> getBestNeighbor(ArrayList<ArrayList<Double>> neighbors) {
//		double neighborSol = -Double.MAX_VALUE;
//		ArrayList<Double> neighborPoint = new ArrayList<>(problemWrapper.getProblem().getDimensions());
//		neighborPoint.add(-Double.MAX_VALUE);
//		neighborPoint.add(-Double.MAX_VALUE);
//		
//		for(ArrayList<Double> point : neighbors) {
//			double nSol = problemWrapper.evaluate(point);
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
