package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

/**
 * Grid implementation of the neighbor factory. This implementation picks
 * 8 neighbors from a grid around the current point as shown below:
 * 
 * N N N
 * N C N
 * N N N
 * 
 * Where C is the current point and each N is a neighbor stepSize
 * away from C.
 */
public class NeighborFactoryGridImpl implements NeighborFactory {

	/**
	 * Picks 8 neighbors from a grid around the point. Each neighbor is stepSize steps from the point.
	 */
	public ArrayList<ArrayList<Double>> getNeighbors(Problem p, ArrayList<Double> point, double stepSize) {
		
		ArrayList<ArrayList<Double>> neighbors = new ArrayList<>();
		
		double x = point.get(0);
		double y = point.get(1);
				
		neighbors.add(getNeighborPoint(p, x, y, stepSize, 0));			// Right of current point.
		neighbors.add(getNeighborPoint(p, x, y, stepSize, stepSize)); 	// Above and right of current point.
		neighbors.add(getNeighborPoint(p, x, y, 0, stepSize)); 			// Above current point.
		neighbors.add(getNeighborPoint(p, x, y, -stepSize, stepSize));	// Above and left of current point.
		neighbors.add(getNeighborPoint(p, x, y, -stepSize, 0)); 			// Left of current point.
		neighbors.add(getNeighborPoint(p, x, y, -stepSize, -stepSize)); 	// Below and left of current point.
		neighbors.add(getNeighborPoint(p, x, y, 0, -stepSize)); 			// Below current point.
		neighbors.add(getNeighborPoint(p, x, y, stepSize, -stepSize)); 	// Below and right of current point.
		
		return neighbors;
	}
	
	/*
	 * Computes a neighbor deltaX and deltaY steps away from the point x,y adhering to the constraints
	 * imposed by the problem. 
	 */
	private ArrayList<Double> getNeighborPoint(Problem p, double x, double y, double deltaX, double deltaY) {
		ArrayList<Double> point = new ArrayList<>(p.getDimensions());

		double minX = p.getMinValues().get(0);
		double maxX = p.getMaxValues().get(0);
		double minY = p.getMinValues().get(1);
		double maxY = p.getMaxValues().get(1);

		double nx = x;
		double ny = y;
		
		// Compute new coordinates.
		if(deltaX != 0) 	nx = x + deltaX;
		if(deltaY != 0) ny = y + deltaY;
		
		// Handle constraints.
		if(nx > maxX) nx = maxX;
		if(nx < minX) nx = minX;
		if(ny > maxY) ny = maxY;
		if(ny < minY) ny = minY;
		
		point.add(nx);
		point.add(ny);
		
		return point;
	}
}
