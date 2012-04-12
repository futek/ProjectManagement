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
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");

		// Browse all projects
		appUITester.selectOption("Browse all projects").expectNothing();

		// Check projects exist
		appUITester.expectOption("[120001] Hello World!");
		appUITester.expectOption("[120002] Goodbye World!");
		appUITester.expectOption("[120003] Good morning World!");
		appUITester.expectOption("[120004] Good afternoon World!");
		appUITester.expectOption("[120005] Good evening World!");

		// Exit menu
		appUITester.selectOption("Back").expectNothing();

		// Sign out
		appUITester.selectOption("Sign out").expect("You signed out.");
	}
}