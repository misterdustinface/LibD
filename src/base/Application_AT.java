package base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Application_AT {
	
	private Application appWithMain;
	private TickingLoop main;
	
	@Before
	public void setUp() throws Exception {
		appWithMain = new Application();
		main = new TickingLoop();
		appWithMain.setMain(main);
	}

	@After
	public void tearDown() throws Exception {
		appWithMain.quit();
	}

	@Test
	public void test_canStartAndQuitAppWithMain() {
		appWithMain.start();
		appWithMain.quit();
	}

}
