package dk.softwarehuset.projectmanagement.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestRegisterWorkHours extends SampleDataSetupWithProjects {
	ApplicationUI appUI = new ApplicationUI(app);
	ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Before
	public void setupActivityOnProject() throws IOException {
		// Sign in as an employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("IJKL").expect("You signed in as \"India Juliet Kilo Lima\".");

		// Find project
		appUITester.selectOption("Browse all projects").expectNothing();
		appUITester.selectOption("[120002] Goodbye World!").expectNothing();

		// Join and register project leader
		appUITester.selectOption("Join project").expect("You've joined the project \"Goodbye World!\".");
		appUITester.selectOption("Register as project leader").expect("You're now project leader for the project \"Goodbye World!\".");

		// Create activity
		appUITester.selectOption("Create activity").expectNothing();
		appUITester.expect("Activity name: ").write("Design").expect("Activity \"Design\" created on project \"Goodbye World!\".");

		// Go back to main menu
		appUITester.selectOption("Back").expectNothing();
		appUITester.selectOption("Back").expectNothing();
		appUITester.selectOption("Back").expectNothing();
		appUITester.expectOption("Sign out");
	}

	@Test
	public void testRegisterWorkHours() throws IOException {
		// Navigate to the activity
		appUITester.selectOption("Register work hours").expectNothing();
		appUITester.expectOption("Personal activities");
		appUITester.selectOption("Projects");
		appUITester.selectOption("[120002] Goodbye World!");
		appUITester.selectOption("Design");
		
		// add the work hours
		appUITester.expect("Time spent on \"Design\": 0");
		appUITester.expect("Amount in minutes: ").write("120").expect("registered 120 minutes on activity \"Design\".");
		assertEquals(120, appUI.getApp().getProjectById("120002").getTotalRegisteredTime());
		
	}
}
