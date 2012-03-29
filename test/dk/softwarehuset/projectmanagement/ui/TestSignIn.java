package dk.softwarehuset.projectmanagement.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.WrongCredentialsException;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;

public class TestSignIn {
	private Application app = new Application();
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testAdminSignIn() throws IOException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee with admin rights
		appUITester.selectOption("Sign In");
		appUITester.promptInput("Employee id: ", "zzzz", "You signed in as \"Administrator\".");

		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());

		// Wrong selection
		appUITester.assertOptionExists("Sign Out");
		appUITester.input("999", "Invalid option selected.");

		// Sign out
		appUITester.selectOption("Sign Out", "You signed out.");

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Exit
		appUITester.selectOption("Exit", "Exited.", true);
	}

	@Test
	public void testEmployeeSignIn() throws IOException, PermissionDeniedException, NonUniqueIdentifierException, WrongCredentialsException {
		// Sample data
		app.signIn("ZZZZ");
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		Employee employee = new Employee(id, name);
		app.addEmployee(employee);
		app.signOut();

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee
		appUITester.selectOption("Sign In");
		appUITester.promptInput("Employee id: ", id, "You signed in as \"" + name + "\".");

		// Check employee is signed in
		assertNotNull(app.getCurrentEmployee());
		assertFalse(app.getCurrentEmployee().isAdmin());

		// Wrong selection
		appUITester.assertOptionExists("Sign Out");
		appUITester.input("999", "Invalid option selected.");

		// Sign out
		appUITester.selectOption("Sign Out", "You signed out.");

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Exit
		appUITester.selectOption("Exit", "Exited.", true);
	}
}