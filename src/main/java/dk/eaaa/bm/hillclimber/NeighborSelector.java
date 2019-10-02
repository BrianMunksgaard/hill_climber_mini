package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

import org.javatuples.Pair;

public interface NeighborSelector {
	
	/**
	 * Pick a new neighbor from the neighborhood.
	 * 
	 * @param currentPoint		The current best point.
	 * @param stepSize			Step size to use when generating neighbors.
	 * @return
	 */
	public Pair<Double, ArrayList<Double>> pickNeighbor(ArrayList<Double> currentPoint, double stepSize);

}
