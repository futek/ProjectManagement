package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestRegisterAsProjectLeader extends SampleDataSetupWithProjects {
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testRegisterAsProjectLeader() throws IOException {
		// Sign in as an employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Check projects exist
		appUITester.selectOption("[120004] Good afternoon World!").expectNothing();

		// Join before registering as project leader
		appUITester.expectNoOption("Register as project leader");
		appUITester.selectOption("Join project").expect("You've joined the project \"Good afternoon World!\".");
		;

		// Register project leader
		appUITester.selectOption("Register as project leader").expect("You're now project leader for the project \"Good afternoon World!\".");
		appUITester.expectNoOption("Register as project leader");

		// Unregister project leader
		appUITester.selectOption("Unregister as project leader").expect("You're no longer project leader for the project \"Good afternoon World!\".");
		appUITester.expectNoOption("Unregister as project leader");

		appUITester.expectOption("Register as project leader");
		appUITester.selectOption("Leave project").expect("You've left the project \"Good afternoon World!\".");
		appUITester.expectNoOption("Register as project leader");
	}

	@Test
	public void testOneProjectLeaderAtATime() throws IOException {
		// Sign in as an employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Select a project
		appUITester.selectOption("[120004] Good afternoon World!").expectNothing();

		// Register project leader
		appUITester.selectOption("Join project").expect("You've joined the project \"Good afternoon World!\".");
		appUITester.selectOption("Register as project leader").expect("You're now project leader for the project \"Good afternoon World!\".");

		// Go back to main menu
		appUITester.selectOption("Back").expectNothing();
		appUITester.selectOption("Back").expectNothing();

		// Sign out
		appUITester.selectOption("Sign out").expect("You signed out.");

		// Sign in as another employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("EFGH").expect("You signed in as \"Echo Foxtrot Golf Hotel\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Select the same project
		appUITester.selectOption("[120004] Good afternoon World!").expectNothing();

		// Check "register as project leader" option doesn't exist
		appUITester.expectNoOption("Register as project leader");
	}

	@Test
	public void testLeaveProjectAsProjectLeader() throws IOException {
		// Sign in as an employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("EFGH").expect("You signed in as \"Echo Foxtrot Golf Hotel\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Check projects exist
		appUITester.selectOption("[120001] Hello World!").expectNothing();

		// Register project leader
		appUITester.selectOption("Join project");
		appUITester.selectOption("Register as project leader").expect("You're now project leader for the project \"Hello World!\".");
		appUITester.expectNoOption("Register as project leader");

		// Leave project leader
		appUITester.selectOption("Leave project").expect("You've left the project \"Hello World!\".");
		appUITester.expectNoOption("Unregister as project leader");
		appUITester.selectOption("Join project").expect("You've joined the project \"Hello World!\".");
		appUITester.expectNoOption("Unregister as project leader");
		appUITester.expectOption("Register as project leader");
	}
}