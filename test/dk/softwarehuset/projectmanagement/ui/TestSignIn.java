package dk.softwarehuset.projectmanagement.ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.WrongCredentialsException;
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
		
		// Wrong selection
		UITestUtil.testScreenInteraction(appUI, "0) Sign Out", "999", "Wrong selection.", false);
		
		// Sign out
		UITestUtil.testScreenInteraction(appUI, "0) Sign Out", "0", "You signed out.", false);
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Exit
		UITestUtil.testScreenInteraction(appUI, "0) Exit", "0", "Exited.", true);
	}
	
	@Test
	public void testEmployeeSignIn() throws IOException, PermissionDeniedException, NonUniqueIdentifierException, WrongCredentialsException {
		// Sample data
		app.SignIn("ZZZZ");
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		Employee employee = new Employee(id, name);
		app.addEmployee(employee);
		app.SignOut();
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as an employee
		UITestUtil.testScreenInteraction(appUI, "1) Sign In", "1", "", false);
		UITestUtil.testScreenInteraction(appUI, "Employee id: ", id, "You signed in as \"" + name + "\".", false);
		
		// Check employee is signed in
		assertNotNull(app.getCurrentEmployee());
		assertFalse(app.getCurrentEmployee().isAdmin());

		// Wrong selection
		UITestUtil.testScreenInteraction(appUI, "0) Sign Out", "999", "Wrong selection.", false);
		
		// Sign out
		UITestUtil.testScreenInteraction(appUI, "0) Sign Out", "0", "You signed out.", false);
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Exit
		UITestUtil.testScreenInteraction(appUI, "0) Exit", "0", "Exited.", true);
	}
}