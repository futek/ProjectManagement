package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.util.UITestUtil;

public class TestExit {
	private Application app = new Application();
	private ApplicationUI appUI = new ApplicationUI(app);
	
	@Test
	public void testExit() throws IOException {
		UITestUtil.testScreenInteraction(appUI, "0) Exit", "0", "Exited.", true);
	}
	@Test
	public void testExitFailWrongOption() throws IOException {
		UITestUtil.testScreenInteraction(appUI, "0) Exit", "mong", "Wrong selection.", false);
	}
}