//package dk.eaaa.bm.hillclimber.crap;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//import dk.eaaa.bm.hillclimber.NeighborFactory;
//import dk.eaaa.bm.hillclimber.Problem;
//
///**
// * Generates a neighborhood using some neighborFactory implementation
// * and picks a random neighbor from that neighborhood.
// */
//public class NeighborFactoryOneRandomFromNeighborhoodImpl implements NeighborFactory {
//
//	NeighborFactory neighborFactory;
//	
//	public NeighborFactoryOneRandomFromNeighborhoodImpl(NeighborFactory neighborFactory) {
//		this.neighborFactory = neighborFactory;
//	}
//	
//	/**
//	 * Picks a random neighbor from the neighborhood and returns it.
//	 */
//	@Override
//	public ArrayList<ArrayList<Double>> getNeighbors(Problem problem, ArrayList<Double> point, double stepSize) {
//
//		ArrayList<ArrayList<Double>> neighbors = neighborFactory.getNeighbors(problem, point, stepSize);
//		ArrayList<Double> neighborPoint = neighbors.get(getRandomIndex(neighbors.size()));
//		
//		ArrayList<ArrayList<Double>> result = new ArrayList<>();
//		result.add(neighborPoint);
//		
//		return result; 
//	}
//
//	private int getRandomIndex(int max) {
//		final Random random = new Random();
//		return random.nextInt(max);
//	}
//}
