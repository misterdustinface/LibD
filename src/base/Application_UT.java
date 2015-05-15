package base;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Application_UT {

	private Application app;
	
	@Before
	public void setUp() throws Exception {
		app = new Application();
	}

	@After
	public void tearDown() throws Exception {
		app.quit();
	}
	
	@Test
	public void test_setAppMain_canStartAndStopMainWithoutExceptions() {
		app.setMain(null);
		app.startComponent("MAIN");
		app.stopComponent("MAIN");
	}
	
	@Test
	public void test_addComponentToApp_canStartAndStopComponentWithoutExceptions() {
		app.addComponent("TEST", null);
		app.startComponent("TEST");
		app.stopComponent("TEST");
	}
	
	@Test
	public void test_setupApp_canStartAndQuitWithoutExceptions() {
		app.setMain(null);
		app.addComponent("TEST", null);
		app.start();
		app.quit();
	}
	
	@Test
	public void test_addComponents_expectAddedComponentNamesVisible() {
		app.addComponent("A", null);
		app.addComponent("B", null);
		app.addComponent("C", null);
		String[] componentNames = app.getComponentNames();
		assertEquals("A", componentNames[0]);
		assertEquals("B", componentNames[1]);
		assertEquals("C", componentNames[2]);
	}

}
