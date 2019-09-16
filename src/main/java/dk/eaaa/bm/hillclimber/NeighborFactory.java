package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

public interface NeighborFactory {

	public ArrayList<ArrayList<Double>> getNeighbors(Problem problem, ArrayList<Double> point, double stepSize);
}
