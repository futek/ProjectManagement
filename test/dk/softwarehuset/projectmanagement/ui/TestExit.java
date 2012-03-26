package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;

public class TestExit {
	private Application app = new Application();
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testExit() throws IOException {
		appUITester.selectOption("Exit", "Exited.", true);
	}

	@Test
	public void testExitFailWrongOption() throws IOException {
		appUITester.assertOptionExists("Exit");
		appUITester.input("999", "Invalid option selected.");
	}
}