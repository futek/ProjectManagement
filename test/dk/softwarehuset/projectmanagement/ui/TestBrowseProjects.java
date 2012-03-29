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
		appUITester.selectOption("Sign In");
		appUITester.promptInput("Employee id: ", "abcd", "You signed in as \"Alpha Bravo Charlie Delta\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects");

		// Check projects exist
		appUITester.assertOptionExists("Hello World!");
		appUITester.assertOptionExists("Goodbye World!");
		appUITester.assertOptionExists("Good morning World!");
		appUITester.assertOptionExists("Good afternoon World!");
		appUITester.assertOptionExists("Good evening World!");

		// Exit menu
		appUITester.selectOption("Exit");

		// Sign out
		appUITester.selectOption("Sign Out");
	}
}