package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

public class ProblemUtil {

//	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProblemUtil.class);
	
	/**
	 * Generates a random starting point for the specified problem.
	 */
	public static ArrayList<Double> getRandomPoint(Problem problem) {
		
		ArrayList<Double> randomPoint = new ArrayList<>(problem.getDimensions());
		
		// Generate random value for each dimension in the point.
		for(int dim = 0; dim < problem.getDimensions(); dim++) {
			
			// Get lower and upper bounds for the current dimension.
			Double minValCurrentDim = problem.getMinValues().get(dim);
			Double maxValCurrentDim = problem.getMaxValues().get(dim);
			
			// Generate random value between lower and upper bounds.
			Double randomDimValue = minValCurrentDim + Math.random() * (maxValCurrentDim - minValCurrentDim);
			randomPoint.add(randomDimValue);
		}
		
		return randomPoint;
	}
}
