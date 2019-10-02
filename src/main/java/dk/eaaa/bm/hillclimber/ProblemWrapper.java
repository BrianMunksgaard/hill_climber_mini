package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

public class ProblemWrapper implements Problem {

	private Problem problem;
	
	public ProblemWrapper(Problem problem) {
		this.problem = problem;
	}
	
	/**
	 * Wrapper for the eval function in the problem class. Used
	 * for counting the number of calls to the eval function and
	 */
	@Override
	public double eval(ArrayList<Double> point) {
		double e = problem.eval(point);
		ProblemNumbers.getInstance().incNoOfSolutionsChecked();
		return e;
	}

	@Override
	public int getDimensions() {
		return problem.getDimensions();
	}

	@Override
	public void setMaxValues(ArrayList<Double> maxVals) {
		problem.setMaxValues(maxVals);
	}

	@Override
	public void setMinValues(ArrayList<Double> minVals) {
		problem.setMinValues(minVals);
	}

	@Override
	public ArrayList<Double> getMaxValues() {
		return problem.getMaxValues();
	}

	@Override
	public ArrayList<Double> getMinValues() {
		return problem.getMinValues();
	}
}
