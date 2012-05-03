package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;
import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestAssignEmployeesToActivity extends SampleDataSetupWithProjects {
	ApplicationUI appUI = new ApplicationUI(app);
	ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Before
	public void setupEmployeesAndActivity() throws InvalidArgumentException, IOException {
		Project project = app.getProjectById("120001");

		// Add employees to project
		project.addEmployee(app.getEmployeeById("EFGH"));
		project.addEmployee(app.getEmployeeById("IJKL"));

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
		appUITester.selectOption("Back").expectNothing();
	}

	@Test
	public void testAssignEmployeesToActivity() throws IOException {
		// Assign employees to activity
		appUITester.selectOption("Assign employees to activity").expectNothing();
		appUITester.expect("0) Back", "1) Design phase", "-> ");
		appUITester.selectOption("Design phase").expectNothing();

		// Assert menu options
		appUITester.expectOption("[EFGH] Echo Foxtrot Golf Hotel");
		appUITester.expectOption("[IJKL] India Juliet Kilo Lima");
		appUITester.expectOption("[ABCD] Alpha Bravo Charlie Delta");
		appUITester.expectNoOption("[ZZZZ] Administrator");

		// Assign employee EFGH
		appUITester.selectOption("[EFGH] Echo Foxtrot Golf Hotel").expect("Employee \"Echo Foxtrot Golf Hotel\" assigned to activity \"Design phase\".");

		// Assert menu options
		appUITester.expectNoOption("[EFGH] Echo Foxtrot Golf Hotel");
		appUITester.expectOption("[IJKL] India Juliet Kilo Lima");
		appUITester.expectOption("[ABCD] Alpha Bravo Charlie Delta");
		appUITester.expectNoOption("[ZZZZ] Administrator");

		// Assign employee IJKL
		appUITester.selectOption("[IJKL] India Juliet Kilo Lima").expect("Employee \"India Juliet Kilo Lima\" assigned to activity \"Design phase\".");

		// Assert menu options
		appUITester.expectNoOption("[EFGH] Echo Foxtrot Golf Hotel");
		appUITester.expectNoOption("[IJKL] India Juliet Kilo Lima");
		appUITester.expectOption("[ABCD] Alpha Bravo Charlie Delta");
		appUITester.expectNoOption("[ZZZZ] Administrator");

		// Go back
		appUITester.selectOption("Back").expectNothing();
	}
}