package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

public class NeighborFactoryMaxtrixImpl implements NeighborFactory {

	public ArrayList<ArrayList<Double>> getNeighbors(Problem p, ArrayList<Double> point) {
		
		ArrayList<ArrayList<Double>> neighbors = new ArrayList<>();
		
		double step = 0.01;
		double x = point.get(0);
		double y = point.get(1);
				

		neighbors.add(getNeighborPoint(p, x, y, step, 0));			// Right of current point.
		neighbors.add(getNeighborPoint(p, x, y, step, step)); 		// Above and right of current point.
		neighbors.add(getNeighborPoint(p, x, y, 0, step)); 			// Above current point.
		neighbors.add(getNeighborPoint(p, x, y, -step, step));		// Above and left of current point.
		neighbors.add(getNeighborPoint(p, x, y, -step, 0)); 		// Left of current point.
		neighbors.add(getNeighborPoint(p, x, y, -step, -step)); 	// Below and left of current point.
		neighbors.add(getNeighborPoint(p, x, y, 0, -step)); 		// Below current point.
		neighbors.add(getNeighborPoint(p, x, y, step, -step)); 		// Below and right of current point.
		
		return neighbors;
	}
	
	private ArrayList<Double> getNeighborPoint(Problem p, double x, double y, double deltaX, double deltaY) {
		ArrayList<Double> point = new ArrayList<>(p.getDimensions());

		double minX = p.getMinValues().get(0);
		double maxX = p.getMaxValues().get(0);
		double minY = p.getMinValues().get(1);
		double maxY = p.getMaxValues().get(1);

		double nx = x;
		double ny = y;
		
		if(deltaX > 0) {
			nx = x + deltaX;
			if(nx > maxX) nx = maxX;
		}
		
		if(deltaX < 0) {
			nx = x -deltaX;
			if(nx < minX) nx = minX;
		}
		
		if(deltaY > 0) {
			ny = y + deltaY;
			if(ny > maxY) ny = maxY;
		}
		
		if(deltaY < 0) {
			ny = y -deltaY;
			if(ny < minY) ny = minY;
		}
		
		point.add(nx);
		point.add(ny);
		
		return point;
	}
}
