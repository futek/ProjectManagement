package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestEditProjectProperties extends SampleDataSetupWithProjects {
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testEditProject() throws IOException {
		// Sign in as an employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Check projects exist
		appUITester.selectOption("[120004] Good afternoon World!").expectNothing();

		// Join project
		appUITester.selectOption("Join project").expect("You've joined the project \"Good afternoon World!\".");

		// Register project leader
		appUITester.selectOption("Register as project leader").expect("You're now project leader for the project \"Good afternoon World!\".");

		// Enter edit project properties screen
		appUITester.selectOption("Edit project properties").expectNothing();

		// Edit name
		appUITester.selectOption("Edit name").expectNothing();
		appUITester.expect("Old project name: Good afternoon World!", "New project name: ").write("libaro").expectNothing();

		// Edit start date
		appUITester.selectOption("Edit start date").expectNothing();
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("5").expectNothing();

		// Edit end date
		appUITester.selectOption("Edit end date").expectNothing();
		appUITester.expect("New end week year: ").write("2012").expectNothing();
		appUITester.expect("New end week number: ").write("6").expectNothing();

		// Fail on setting start date. After end date
		appUITester.selectOption("Edit start date").expectNothing();
		appUITester.expect("Old start week year: 2012", "New start week year: ").write("2012").expectNothing();
		appUITester.expect("Old start week number: 5", "New start week number: ").write("7").expect("Start date after end date. Try again.");

		// Fail on setting end date. Before start date
		appUITester.selectOption("Edit end date").expectNothing();
		appUITester.expect("Old end week year: 2012", "New end week year: ").write("2012").expectNothing();
		appUITester.expect("Old end week number: 6", "New end week number: ").write("4").expect("End date before start date. Try again.");
	}
}
