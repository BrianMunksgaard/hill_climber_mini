//package dk.eaaa.bm.hillclimber;
//
//import java.util.ArrayList;
//import org.javatuples.Pair;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.event.Level;
//
//@RunWith(JUnit4.class)
//public class ImprovedHillClimberTestsNeighborFactoryCircumferenceImpl {
//	
//	@Test
//	public void testSolutionForP1() {
//		Problem p = new P1();
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.01, 10);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.01, 100);
//		
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.1, 10);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.1, 100);
//	}
//
//	@Test
//	public void testSolutionForP2() {
//		Problem p = new P2();
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.01, 10);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.01, 100);
//		
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.1, 10);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.1, 100);
//	}
//
//	@Test
//	public void testSolutionForRevAckleyUsingNeighborFactoryGridImpl() {
//		Problem p = new RevAckley();
//		
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.001, 10);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.001, 100);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 200, 0.001, 200);
//
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.01, 10);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.01, 100);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 200, 0.01, 200);
//		
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.1, 10);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.1, 100);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 200, 0.1, 200);
//	}
//	
//	
//	private Pair<Double, ArrayList<Double>> findSolutionUsingNeighborFactoryCircumferenceImpl(Problem p, int iterations, double stepSize, int neighbors) {
//		NeighborFactory neighborFactory = new NeighborFactoryCircumferenceImpl(neighbors);
//		String opName = 
//				neighborFactory.getClass().getSimpleName() + " - "
//				+ p.getClass().getSimpleName() + ", "
//				+ "Iter=" + Integer.toString(iterations) + ", " 
//				+ "StepSize=" + Double.toString(stepSize); 
//				
//		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
//        String loggerName = this.getClass().getName() + methodName;
//        Logger log = LoggerFactory.getLogger(loggerName);
//    
//		TimerUtil tu = TimerUtil.start(log, Level.INFO, opName);
//		ImprovedHillClimbing solver = new ImprovedHillClimbing(p, neighborFactory);
//		ArrayList<Double> result = solver.findOptima(iterations, stepSize);
//		tu.stop();
//		Double best = p.eval(result);
//		log.info("");
//		return Pair.with(best, result);
//	}
//}
