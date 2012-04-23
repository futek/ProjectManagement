package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestEditActivityProperties extends SampleDataSetupWithProjects {
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
	public void testEditActivityName() throws IOException {
		// Find the project
		appUITester.selectOption("Browse all projects").expectNothing();
		appUITester.selectOption("[120002] Goodbye World!").expectNothing();

		// Find the activity
		appUITester.selectOption("Browse activities").expectNothing();
		appUITester.selectOption("Design").expectNothing();

		// Edit name
		appUITester.selectOption("Edit properties").expectNothing();
		appUITester.selectOption("Edit name").expectNothing();
		appUITester.expect("Old activity name: Design", "New activity name: ").write("Implementation").expect("Activity \"Design\" changed to \"Implementation\".");
		appUITester.selectOption("Back").expectNothing();
		appUITester.selectOption("Back").expectNothing();

		// Check that it changed name
		appUITester.selectOption("Browse activities").expectNothing();
		appUITester.expectNoOption("Design");
		appUITester.expectOption("Implementation");
	}

	@Test
	public void testEditActivityDates() throws Exception {
		// Find the project
		appUITester.selectOption("Browse all projects").expectNothing();
		appUITester.selectOption("[120002] Goodbye World!").expectNothing();

		// Find the activity and edit properties
		appUITester.selectOption("Browse activities").expectNothing();
		appUITester.selectOption("Design").expectNothing();
		appUITester.selectOption("Edit properties").expectNothing();

		// Set start date
		appUITester.selectOption("Edit start date").expectNothing();
		appUITester.expect("New start year: ").write("2012").expectNothing();
		appUITester.expect("New start week: ").write("4").expectNothing();

		// Set end date
		appUITester.selectOption("Edit end date").expectNothing();
		appUITester.expect("New end year: ").write("2012").expectNothing();
		appUITester.expect("New end week: ").write("6").expectNothing();

		// Edit end date
		appUITester.selectOption("Edit end date").expectNothing();
		appUITester.expect("Old end year: 2012", "New end year: ").write("2013").expectNothing();
		appUITester.expect("Old end week: 6", "New end week: ").write("3").expectNothing();

		// Edit start date
		appUITester.selectOption("Edit start date").expectNothing();
		appUITester.expect("Old start year: 2012", "New start year: ").write("2012").expectNothing();
		appUITester.expect("Old start week: 4", "New start week: ").write("1").expectNothing();
	}
}