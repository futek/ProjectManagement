package dk.softwarehuset.projectmanagement.ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
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
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ZZZZ").expect("You signed in as \"Administrator\".");

		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());

		// Wrong selection
		appUITester.expectOption("Sign out").write("999").expect("Invalid option selected.");

		// Sign out
		appUITester.selectOption("Sign out").expect("You signed out.");

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Exit
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testEmployeeSignIn() throws IOException, PermissionDeniedException, NonUniqueIdentifierException, WrongCredentialsException {
		// Sample data
		app.signIn("ZZZZ");
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		app.createEmployee(id, name);
		app.signOut();

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write(id).expect("You signed in as \"" + name + "\".");

		// Check employee is signed in
		assertNotNull(app.getCurrentEmployee());
		assertFalse(app.getCurrentEmployee().isAdmin());

		// Wrong selection
		appUITester.expectOption("Sign out").write("999").expect("Invalid option selected.");

		// Sign out
		appUITester.selectOption("Sign out").expect("You signed out.");

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Exit
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}
}