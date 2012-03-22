package dk.softwarehuset.projectmanagement.ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.util.UITestUtil;

public class TestSignIn {
	private Application app = new Application();
	private ApplicationUI appUI = new ApplicationUI(app);
	
	@Test
	public void testAdminSignIn() throws IOException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as an employee with admin rights
		UITestUtil.testScreenInteraction(appUI, "1) Sign In", "1", "", false);
		UITestUtil.testScreenInteraction(appUI, "Employee id: ", "ZZZZ", "You signed in as \"Administrator\".", false);
		
		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());
		
		// Sign out
		UITestUtil.testScreenInteraction(appUI, "0) Sign Out", "0", "You signed out.", false);
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		UITestUtil.testScreenInteraction(appUI, "0) Exit", "0", "Exited.", true);
	}
	
	@Test
	public void test() throws IOException {
		//UITestUtil.testScreenInteraction(appUI, "1) Sign In", "1", "employee id: ", false);
	}
}