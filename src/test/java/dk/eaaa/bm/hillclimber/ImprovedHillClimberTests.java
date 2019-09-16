package dk.eaaa.bm.hillclimber;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.event.Level;


@RunWith(JUnit4.class)
public class ImprovedHillClimberTests {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass().getName());	
	
	private double stepSize = 0.001;
	
	@Test
	public void testSolutionForP1() {
		TimerUtil tu = TimerUtil.start(log, Level.INFO, "Solving P1.");
		Problem p = new P1();
		NeighborFactory neighborFactory = new NeighborFactoryMaxtrixImpl();
		ImprovedHillClimbing solver = new ImprovedHillClimbing(p, neighborFactory, stepSize);
		solver.findOptima(5);
		tu.stop();
	}

	@Test
	public void testSolutionForP2() {
		TimerUtil tu = TimerUtil.start(log, Level.INFO, "Solving P2.");
		Problem p = new P2();
		NeighborFactory neighborFactory = new NeighborFactoryMaxtrixImpl();
		ImprovedHillClimbing solver = new ImprovedHillClimbing(p, neighborFactory, stepSize);
		solver.findOptima(5);
		tu.stop();
	}

	@Test
	public void testSolutionForRevAckley() {
		TimerUtil tu = TimerUtil.start(log, Level.INFO, "Solving RevAckley.");
		Problem p = new RevAckley();
		NeighborFactory neighborFactory = new NeighborFactoryMaxtrixImpl();
		ImprovedHillClimbing solver = new ImprovedHillClimbing(p, neighborFactory, stepSize);
		solver.findOptima(1000);
		tu.stop();
	}
}
