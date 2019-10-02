package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

public interface Problem {

	public abstract double eval(ArrayList<Double> paramVals);

	public abstract int getDimensions();

	public void setMaxValues(ArrayList<Double> maxVals);

	public void setMinValues(ArrayList<Double> minVals);

	public ArrayList<Double> getMaxValues();

	public ArrayList<Double> getMinValues();
}
