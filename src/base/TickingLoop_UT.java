package base;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TickingLoop_UT {

	private TickingLoop loop;
	
	@Before
	public void setUp() throws Exception {
		loop = new TickingLoop();
		loop.setUpdatesPerSecond(0);
	}

	@Test
	public void test_setUpdatesPerSecond_expectPausedWhenUPSLessThanOrEqualTo0() {
		loop.setUpdatesPerSecond(60);
		assertFalse(loop.isPaused());
		loop.setUpdatesPerSecond(0);
		assertTrue(loop.isPaused());
		loop.setUpdatesPerSecond(-60);
		assertTrue(loop.isPaused());
		loop.setUpdatesPerSecond(1);
		assertFalse(loop.isPaused());
	}
	
	@Test (expected=NullPointerException.class)
	public void test_addNullFunction_expectNullPointerException() {
		loop.addFunction(null);
	}
	
}
