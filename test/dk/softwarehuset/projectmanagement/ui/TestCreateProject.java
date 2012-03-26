package dk.softwarehuset.projectmanagement.ui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.DateServer;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.WrongCredentialsException;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetup;

public class TestCreateProject extends SampleDataSetup {
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);
	
	@Test
	public void testCreateProject() throws WrongCredentialsException {		
		// Check no projects exist
		assertEquals(0, app.getProjects().size());
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as employee
		appUITester.selectOption("Sign In", "");
		appUITester.input("Employee id: ", "ABCD");
		
  	// Check employee is signed in and not admin
		assertNotNull(app.getCurrentEmployee());
		assertEquals("ABCD", app.getCurrentEmployee().getId());
		assertEquals("Alpha Bravo Charlie Delta", app.getCurrentEmployee().getName());
		assertFalse(app.getCurrentEmployee().isAdmin());
				
		// Add new project
		String name = "Hello World"; 
		appUITester.selectOption("Create Project", "");
		appUITester.input("New project name: ", name, "Project \"" + name + "\" created.");
		
		// Check project exists
		assertEquals(1, app.getProjects().size());
		assertEquals("120001", app.getProjects().get("120001").getId());
		assertEquals(name, app.getProjects().get("120001").getName());
		
		// Sign out
		appUITester.selectOption("Sign Out", "You signed out.");
		appUITester.input("New project name: ", name, "Project \"" + name + "\" created.");
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Exit
		appUITester.selectOption("Exit", "Exited.", true);
	}
}