package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestJoinAndLeaveProject extends SampleDataSetupWithProjects {
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testJoinProject() throws IOException {
		// Sign in as an employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Check projects exist
		appUITester.selectOption("[120001] Hello World!").expectNothing();
		
		// Join project
		appUITester.selectOption("Join project").expect("You've joined the project \"Hello World!\".");
	}
	
	@Test
	public void testLeaveProject() throws IOException {
		// Sign in as an employee
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Check projects exist
		appUITester.selectOption("[120001] Hello World!").expectNothing();
		
		// Join project
		appUITester.selectOption("Join project").expect("You've joined the project \"Hello World!\".");
		
		// Leave project
		appUITester.selectOption("Leave project").expect("You've left the project \"Hello World!\".");
	}
}
