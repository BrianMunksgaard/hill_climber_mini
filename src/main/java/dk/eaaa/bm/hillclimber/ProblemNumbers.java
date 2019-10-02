package dk.eaaa.bm.hillclimber;

public class ProblemNumbers {

	private static ProblemNumbers instance = new ProblemNumbers();
	
	private int noOfSolutionsChecked;
	private int noOfBetterSolutions;
	private int noOfNeighborsPicked;

	
	private ProblemNumbers() {}
	
	public static ProblemNumbers getInstance() {
		return instance;
	}

	public void reset() {
		noOfSolutionsChecked = 0;
		noOfBetterSolutions = 0;
		noOfNeighborsPicked = 0;
	}
	
	public int getNoOfSolutionsChecked() {
		return noOfSolutionsChecked;
	}
	
	public int getNoOfBetterSolutions() {
		return noOfBetterSolutions;
	}
	
	public int getNoOfNeighborsPicked() {
		return noOfNeighborsPicked;
	}

	public void incNoOfSolutionsChecked() {
		noOfSolutionsChecked++;
	}

	public void incNoOfBetterSolutions() {
		noOfBetterSolutions++;
	}
	
	public void incNoOfNeighborsPicked() {
		noOfNeighborsPicked++;
	}
}
