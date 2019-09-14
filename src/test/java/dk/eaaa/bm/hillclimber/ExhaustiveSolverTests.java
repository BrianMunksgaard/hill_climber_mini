package dk.eaaa.bm.hillclimber;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.event.Level;


@RunWith(JUnit4.class)
public class ExhaustiveSolverTests {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass().getName());
	
	private int solutionsChecked;
	
	@Test
	public void testExhaustiveSolutionForP1() {
		TimerUtil tu = TimerUtil.start(log, Level.INFO, "P1 Test");
		log.info("Starting exhaustive search for P1 problem.");
		Problem p = new P1();
		solutionsChecked = 0;
		testExhaustiveSolution(p);
		tu.stop();
	}
	

	@Test
	public void testExhaustiveSolutionForP2() {
		TimerUtil tu = TimerUtil.start(log, Level.INFO, "P2 Test");
		log.info("Starting exhaustive search for P2 problem.");
		Problem p = new P2();
		solutionsChecked = 0;
		testExhaustiveSolution(p);
		tu.stop();
	}

	@Test
	public void testExhaustiveSolutionForRevAckley() {
		TimerUtil tu = TimerUtil.start(log, Level.INFO, "RevAckley Test");
		log.info("Starting exhaustive search for RevAckley problem.");
		Problem p = new RevAckley();
		solutionsChecked = 0;
		testExhaustiveSolution(p);
		tu.stop();
	}

	private void testExhaustiveSolution(Problem p) {
		
		ArrayList<Double> bestPoint = new ArrayList<>(2);
		double minX = p.getMinValues().get(0);
		double maxX = p.getMaxValues().get(0) + 0.001;
		double minY = p.getMinValues().get(1);
		double maxY = p.getMaxValues().get(1) + 0.001;

		int minXInt = Double.valueOf(p.getMinValues().get(0) * 100).intValue();
		int maxXInt = Double.valueOf(p.getMaxValues().get(0) * 100).intValue();
		int minYInt = Double.valueOf(p.getMinValues().get(1) * 100).intValue();
		int maxYInt = Double.valueOf(p.getMaxValues().get(1) * 100).intValue();
		int maxChecks = (maxXInt - minXInt) * (maxYInt - minYInt) * 2;
		log.info("Minimum checks for exhaustive search: {}.", maxChecks );
		
		bestPoint.add(minX);
		bestPoint.add(minY);
		Double bestSol = Double.MIN_VALUE;
	
		
		for( double x = minX; x <= maxX; x+= 0.01) {

			for( double y = minY; y <= maxY; y+= 0.01) {
				double sol = getSol(p, x, y);
				if (sol > bestSol) {
					bestSol = sol;
					bestPoint.set(0, x);
					bestPoint.set(1, y);
				}
				sol = getSol(p, y, x);
				if (sol > bestSol) {
					bestSol = sol;
					bestPoint.set(0, y);
					bestPoint.set(1, x);
				}
			}
		}
		
		String msg = String.format("Best solution is: %.2f, %.2f = %.2f", bestPoint.get(0), bestPoint.get(1), bestSol);
		log.info("Number of solutions checked: {}.", solutionsChecked);
		log.info(msg);
	}
	
	private double getSol(Problem p, double x, double y) {
		ArrayList<Double> currentPoint = new ArrayList<>();
		currentPoint.add(x);
		currentPoint.add(y);
		double sol = p.eval(currentPoint); 
		//String msg = String.format("Solution %d for %.2f, %.2f = %f", ++solutionsChecked, x, y, sol);
		//log.info(msg);
		return sol;
	}

}
