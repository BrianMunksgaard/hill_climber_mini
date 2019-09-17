package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Circumference implementation of the neighbor factory. This implementation picks
 * a specified number of neighbors from the circumference of the current point.
 */
public class NeighborFactoryCircumferenceImpl implements NeighborFactory {

	private final int noOfNeighbors;
	
	public NeighborFactoryCircumferenceImpl(int noOfNeighbors) {
		this.noOfNeighbors = noOfNeighbors;
		if(this.noOfNeighbors < 1) noOfNeighbors = 1;
	}
	
	/**
	 * Picks neighbors from the circumference of the point using stepSize as the radius.
	 */
	public ArrayList<ArrayList<Double>> getNeighbors(Problem p, ArrayList<Double> point, double stepSize) {
		
		double x = point.get(0);
		double y = point.get(1);
		
		ArrayList<ArrayList<Double>> neighbors = new ArrayList<>(noOfNeighbors);
		double radian = (2 * 22.0 / 7.0) / noOfNeighbors;

		for (int i = 0; i < noOfNeighbors; i++) {
			double t = (i + 1) * radian;
			double nx = x + stepSize * Math.cos(t);
			double ny = y + stepSize * Math.sin(t);
			ArrayList<Double> nPoint = new ArrayList<>(Arrays.asList(nx, ny));
			neighbors.add(nPoint);
		}
		return neighbors;
	}
}
