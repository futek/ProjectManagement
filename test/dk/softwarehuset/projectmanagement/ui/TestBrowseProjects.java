package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestBrowseProjects extends SampleDataSetupWithProjects {
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testBrowseAllProjects() throws IOException {
		// Sign in as an employee
		appUITester.selectOption("Sign In").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Check projects exist
		appUITester.expectOption("Hello World!");
		appUITester.expectOption("Goodbye World!");
		appUITester.expectOption("Good morning World!");
		appUITester.expectOption("Good afternoon World!");
		appUITester.expectOption("Good evening World!");

		// Exit menu
		appUITester.selectOption("Exit").expectNothing();

		// Sign out
		appUITester.selectOption("Sign Out").expect("You signed out.");
	}
}