package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestRegisterWorkHours extends SampleDataSetupWithProjects {
	ApplicationUI appUI = new ApplicationUI(app);
	ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Before
	public void setupActivities() throws IOException, InvalidArgumentException {
		// Sign in as an employee IJKL
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("IJKL").expect("You signed in as \"India Juliet Kilo Lima\".");

		// Find project
		appUITester.selectOption("Browse all projects").expectNothing();
		appUITester.selectOption("[120002] Goodbye World!").expectNothing();

		// Join and register project leader
		appUITester.selectOption("Join project").expect("You've joined the project \"Goodbye World!\".");
		appUITester.selectOption("Register as project leader").expect("You're now project leader for the project \"Goodbye World!\".");

		// Create activity on project
		appUITester.selectOption("Create activity").expectNothing();
		appUITester.expect("Activity name: ").write("Design").expect("Activity \"Design\" created on project \"Goodbye World!\".");
		appUITester.selectOption("Back").expectNothing();

		// Assign employee IJKL to activity
		appUITester.selectOption("Assign employees to activity").expectNothing();
		appUITester.selectOption("Design").expectNothing();
		appUITester.selectOption("[IJKL] India Juliet Kilo Lima").expect("Employee \"India Juliet Kilo Lima\" assigned to activity \"Design\".");
		appUITester.selectOption("Back").expectNothing();

		// Go back to main menu
		appUITester.selectOption("Back").expectNothing();
		appUITester.selectOption("Back").expectNothing();
		appUITester.selectOption("Back").expectNothing();
		appUITester.expectOption("Sign out");

		// Create activity on employee
		appUITester.selectOption("Create activity").expectNothing();
		appUITester.expect("Activity name: ").write("Some course").expect("Activity \"Some course\" created.");

		// Go back to main menu
		appUITester.selectOption("Back").expectNothing();
		appUITester.expectOption("Sign out");
	}

	@Test
	public void testRegisterTimeOnProjectActivity() throws IOException {
		// Navigate to the activity
		appUITester.selectOption("Register work hours").expectNothing();
		appUITester.expectOption("Personal activities");
		appUITester.selectOption("Project activities").expectNothing();
		appUITester.expect("0) Back", "1) [120002] Goodbye World!", "-> "); // Check projects were filtered
		appUITester.selectOption("[120002] Goodbye World!").expectNothing();
		appUITester.selectOption("Design").expectNothing(); // TODO: Check filter?

		// Add the work hours
		appUITester.expect(
				"Time spent on activity \"Design\" today: 0 min",
				"Register time (in minutes): ")
				.write("120").expect("Time spent on activity \"Design\" today: 120 min");

		// Navigate to the same activity again
		appUITester.selectOption("Register work hours").expectNothing();
		appUITester.expectOption("Personal activities");
		appUITester.selectOption("Project activities").expectNothing();
		appUITester.selectOption("[120002] Goodbye World!").expectNothing();
		appUITester.selectOption("Design").expectNothing();

		// Register more time
		appUITester.expect(
				"Time spent on activity \"Design\" today: 120 min",
				"Register time (in minutes): ")
				.write("30").expect("Time spent on activity \"Design\" today: 150 min");
	}

	@Test
	public void testRegisterTimeOnPersonalActivity() throws IOException {
		// Navigate to activity
		appUITester.selectOption("Register work hours").expectNothing();
		appUITester.expectOption("Project activities");
		appUITester.selectOption("Personal activities").expectNothing();
		appUITester.expect("0) Back", "1) Some course", "-> ");
		appUITester.selectOption("Some course").expectNothing();

		// Add the work hours
		appUITester.expect(
				"Time spent on activity \"Some course\" today: 0 min",
				"Register time (in minutes): ")
				.write("60").expect("Time spent on activity \"Some course\" today: 60 min");

		// Navigate to activity
		appUITester.selectOption("Register work hours").expectNothing();
		appUITester.selectOption("Personal activities").expectNothing();
		appUITester.expect("0) Back", "1) Some course", "-> ");
		appUITester.selectOption("Some course").expectNothing();

		// Register more time
		appUITester.expect(
				"Time spent on activity \"Some course\" today: 60 min",
				"Register time (in minutes): ")
				.write("30").expect("Time spent on activity \"Some course\" today: 90 min");
	}
}
