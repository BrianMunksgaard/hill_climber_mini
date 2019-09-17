//package dk.eaaa.bm.hillclimber;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.event.Level;
//
//@RunWith(JUnit4.class)
//public class ImprovedHillClimberTestsNeighborFactoryGridImpl {
//	
//	@Test
//	public void testSolutionForP1() {
//		Problem p = new P1();
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.01);
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.01);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.1);
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.1);
//	}
//
//	@Test
//	public void testSolutionForP2() {
//		Problem p = new P2();
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.01);
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.01);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.1);
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.1);
//	}
//
//	@Test
//	public void testSolutionForRevAckleyUsingNeighborFactoryGridImpl() {
//		
//		Problem p = new RevAckley();
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.001);
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.001);
//		findSolutionUsingNeighborFactoryGridImpl(p, 200, 0.001);
//
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.01);
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.01);
//		findSolutionUsingNeighborFactoryGridImpl(p, 200, 0.01);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.1);
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.1);
//		findSolutionUsingNeighborFactoryGridImpl(p, 200, 0.1);
//	}
//	
//	private void findSolutionUsingNeighborFactoryGridImpl(Problem p, int iterations, double stepSize) {
//		
//		NeighborFactory neighborFactory = new NeighborFactoryGridImpl();
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
//		solver.findOptima(iterations, stepSize);
//		tu.stop();
//		log.info("");
//	}
//
//}
