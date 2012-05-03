package dk.softwarehuset.projectmanagement.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.DateServer;
import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.TooManyProjectsException;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;

public class SystestAssignEmployeesToActivity {
	Application app = new Application();
	ApplicationUI appUI = new ApplicationUI(app);
	ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Before
	public void setup() throws PermissionDeniedException, InvalidArgumentException, TooManyProjectsException, IOException {
		app.signIn("ZZZZ");

		// Set date
		DateServer dateServer = mock(DateServer.class);
		app.setDateServer(dateServer);
		LocalDate date = new LocalDate(2012, 3, 26);
		when(dateServer.getDate()).thenReturn(date);

		// Create employees
		Employee abcd = app.createEmployee("ABCD", "Alpha Bravo Charlie Delta");
		Employee efgh = app.createEmployee("EFGH", "Echo Foxtrot Golf Hotel");
		Employee ijkl = app.createEmployee("IJKL", "India Juliet Kilo Lima");
		Employee mnop = app.createEmployee("MNOP", "Mike November Oscar Papa");
		Employee qrst = app.createEmployee("QRST", "Quebec Romeo Sierra Tango");

		// Create project
		Project project = app.createProject("Project #1");

		// Create activity
		Activity activity = app.createActivity(project, "Activity #1");

		// Make IJKL, MNOP and QRST join the project
		project.addEmployee(ijkl);
		project.addEmployee(mnop);
		project.addEmployee(qrst);

		// Pre-assign QRST to activity
		qrst.addActivity(activity);

		app.signOut();

		// Sign in as employee ABCD
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");
		appUITester.selectOption("Browse all projects").expectNothing();
		appUITester.selectOption("[120001] Project #1").expectNothing();
		appUITester.selectOption("Join project").expect("You've joined the project \"Project #1\".");
		appUITester.selectOption("Register as project leader").expect("You're now project leader for the project \"Project #1\".");
		appUITester.selectOption("Assign employees to activity").expectNothing();
		appUITester.selectOption("Activity #1").expectNothing();
	}

	@Test
	public void testA() throws IOException {
		appUITester.expectNoOption("[UVWX] Uniform Victor Whiskey Xray"); // Nonexistent employee
	}

	@Test
	public void testB() throws IOException {
		appUITester.expectNoOption("[EFGH] Echo Foxtrot Golf Hotel"); // Employee not on project
	}

	@Test
	public void testC() throws IOException {
		appUITester.selectOption("[IJKL] India Juliet Kilo Lima").expect("Employee \"India Juliet Kilo Lima\" assigned to activity \"Activity #1\"."); // Employee on project and not on activity
		appUITester.expectNoOption("[UVWX] Uniform Victor Whiskey Xray"); // Nonexistent employee
	}

	@Test
	public void testD() throws IOException {
		appUITester.selectOption("[IJKL] India Juliet Kilo Lima").expect("Employee \"India Juliet Kilo Lima\" assigned to activity \"Activity #1\"."); // Employee on project and not on activity
		appUITester.expectNoOption("[EFGH] Echo Foxtrot Golf Hotel"); // Employee not on project
	}

	@Test
	public void testE() throws IOException {
		appUITester.selectOption("[IJKL] India Juliet Kilo Lima").expect("Employee \"India Juliet Kilo Lima\" assigned to activity \"Activity #1\"."); // Employee on project and not on activity
		appUITester.selectOption("[MNOP] Mike November Oscar Papa").expect("Employee \"Mike November Oscar Papa\" assigned to activity \"Activity #1\"."); // Employee on project and not on activity
	}

	@Test
	public void testF() throws IOException {
		appUITester.selectOption("[IJKL] India Juliet Kilo Lima").expect("Employee \"India Juliet Kilo Lima\" assigned to activity \"Activity #1\"."); // Employee on project and not on activity
		appUITester.expectNoOption("[IJKL] Quebec Romeo Sierra Tango"); // Employee on project and on activity
	}

	@Test
	public void testG() throws IOException {
		appUITester.expectNoOption("[IJKL] Quebec Romeo Sierra Tango"); // Employee on project and on activity
	}
}