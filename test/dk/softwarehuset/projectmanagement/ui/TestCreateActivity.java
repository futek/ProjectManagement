package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestCreateActivity extends SampleDataSetupWithProjects {
	ApplicationUI appUI = new ApplicationUI(app);
	ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testCreateActivityOnProject() throws IOException {
		// Sign in as employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// View a project
		appUITester.selectOption("Browse all projects").expectNothing();
		appUITester.selectOption("[120001] Hello World!").expectNothing();
		
		// Join project
		appUITester.selectOption("Join project").expect("You've joined the project \"Hello World!\".");

		// Register as project leader
		appUITester.selectOption("Register as project leader").expect("You're now project leader for the project \"Hello World!\".");

		// Create new activity on project
		appUITester.selectOption("Create activity").expectNothing();
		appUITester.expect("Activity name: ").write("Design phase").expect("Activity \"Design phase\" created on project \"Hello World!\".");

		// Leave view activity screen
		appUITester.selectOption("Back").expectNothing();

		// Go back to main menu
		appUITester.selectOption("Back").expectNothing();
		appUITester.selectOption("Back").expectNothing();

		// Sign out
		appUITester.selectOption("Sign out").expect("You signed out.");

		// Exit
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testCreateActivityOnEmployee() throws IOException {
		// Sign in as employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// Create new activity on employee
		appUITester.selectOption("Create activity").expectNothing();
		appUITester.expect("Activity name: ").write("Some course").expect("Activity \"Some course\" created.");

		// Leave view activity screen
		appUITester.selectOption("Back").expectNothing();

		// Sign out
		appUITester.selectOption("Sign out").expect("You signed out.");

		// Exit
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}
}