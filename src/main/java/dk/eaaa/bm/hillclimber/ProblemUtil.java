package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

public class ProblemUtil {

//	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProblemUtil.class);
	
	/**
	 * Generates a random starting point for the specified problem.
	 */
	public static ArrayList<Double> getRandomPoint(Problem problem) {
		
		ArrayList<Double> initialPoint = new ArrayList<>(problem.getDimensions());
		
		// Generate random value for each dimension in the point.
		for(int dim = 0; dim < problem.getDimensions(); dim++) {
			
			// Get lower and upper bounds for the current dimension.
			Double minValCurrentDim = problem.getMinValues().get(dim);
			Double maxValCurrentDim = problem.getMaxValues().get(dim);
			
			// Generate random value between lower and upper bounds.
			Double randomDimValue = minValCurrentDim + Math.random() * (maxValCurrentDim - minValCurrentDim);
			initialPoint.add(randomDimValue);
		}
		
		return initialPoint;
	}

//	/**
//	 * The Problem class uses an ArrayList to represent coordinates. With
//	 * this utility method the evaluation function for the problem can
//	 * be called with x and y coordinates.
//	 * 
//	 * NOTE: Assumes only two dimensions in the problem.
//	 */
//	public static double getSol(Problem p, double x, double y) {
//		ArrayList<Double> currentPoint = new ArrayList<>();
//		currentPoint.add(x);
//		currentPoint.add(y);
//		double sol = p.eval(currentPoint); 
//		if(log.isTraceEnabled()) {
//			String msg = String.format("Solution %d for %.2f, %.2f = %f", solutionsChecked, x, y, sol);
//			log.trace(msg);
//		}
//		return sol;
//	}
}
