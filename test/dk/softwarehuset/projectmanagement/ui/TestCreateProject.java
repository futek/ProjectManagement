package dk.softwarehuset.projectmanagement.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.WrongCredentialsException;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetup;

public class TestCreateProject extends SampleDataSetup {
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testCreateProject() throws WrongCredentialsException, IOException {
		// Check no projects exist
		assertEquals(0, app.getProjects().size());

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as employee
		appUITester.selectOption("Sign In");
		appUITester.promptInput("Employee id: ", "ABCD", "You signed in as \"Alpha Bravo Charlie Delta\".");

		// Check employee is signed in and not admin
		assertNotNull(app.getCurrentEmployee());
		assertEquals("ABCD", app.getCurrentEmployee().getId());
		assertEquals("Alpha Bravo Charlie Delta", app.getCurrentEmployee().getName());
		assertFalse(app.getCurrentEmployee().isAdmin());

		// Add new project
		String name = "Hello World";
		appUITester.selectOption("Create Project");
		appUITester.promptInput("New project name: ", name, "Project \"" + name + "\" created.");

		// Check project exists
		assertEquals(1, app.getProjects().size());
		assertEquals(name, app.getProjects().get("120001").getName());

		// Exit edit project properties screen
		appUITester.selectOption("Exit");

		// Sign out
		appUITester.selectOption("Sign Out", "You signed out.");

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Exit
		appUITester.selectOption("Exit", "Exited.", true);
	}

	@Test
	public void testCreateProjectFailTooManyProjects() throws IOException {
		// Check no projects exist
		assertEquals(0, app.getProjects().size());

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as employee
		appUITester.selectOption("Sign In");
		appUITester.promptInput("Employee id: ", "ABCD", "You signed in as \"Alpha Bravo Charlie Delta\".");

		// Check employee is signed in and not admin
		assertNotNull(app.getCurrentEmployee());
		assertEquals("ABCD", app.getCurrentEmployee().getId());
		assertEquals("Alpha Bravo Charlie Delta", app.getCurrentEmployee().getName());
		assertFalse(app.getCurrentEmployee().isAdmin());

		// Create 9999 projects
		for (int i = 1; i <= 9999; i++) {
			appUITester.selectOption("Create Project");
			appUITester.promptInput("New project name: ", "Project #" + i, "Project \"Project #" + i + "\" created.");
			appUITester.selectOption("Exit");
		}

		// Attempt to create the 10000th project
		appUITester.selectOption("Create Project");
		appUITester.promptInput("New project name: ", "Project #10000", "Limit of 9999 projects reached, wait until new year.");

		// Sign out
		appUITester.selectOption("Sign Out", "You signed out.");

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Exit
		appUITester.selectOption("Exit", "Exited.", true);
	}
}