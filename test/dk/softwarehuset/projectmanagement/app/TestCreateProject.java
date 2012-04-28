package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.SampleDataSetup;

public class TestCreateProject extends SampleDataSetup {
	@Test
	public void testCreateProject() throws InvalidArgumentException, PermissionDeniedException, TooManyProjectsException {
		// Check no projects exist
		assertEquals(0, app.getProjects().size());

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as employee
		app.signIn("ABCD");

		// Check employee is signed in and not admin
		assertNotNull(app.getCurrentEmployee());
		assertEquals(app.getCurrentEmployee().getId(), "ABCD");
		assertEquals(app.getCurrentEmployee().getName(), "Alpha Bravo Charlie Delta");
		assertFalse(app.getCurrentEmployee().isAdmin());

		// Add new project
		String name = "Hello World";
		app.createProject(name);

		// Check project exists
		assertNotNull(app.getProjectById("120001"));

		// Sign out
		app.signOut();

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
	}

	@Test(expected = TooManyProjectsException.class)
	public void testCreateProjectFailTooManyProjects() throws InvalidArgumentException, PermissionDeniedException, TooManyProjectsException {
		// Check no projects exist
		assertEquals(0, app.getProjects().size());

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as employee
		app.signIn("ABCD");

		// Check employee is signed in and not admin
		assertNotNull(app.getCurrentEmployee());
		assertEquals(app.getCurrentEmployee().getId(), "ABCD");
		assertEquals(app.getCurrentEmployee().getName(), "Alpha Bravo Charlie Delta");
		assertFalse(app.getCurrentEmployee().isAdmin());

		// Add 9999 projects
		for (int i = 1; i <= 9999; i++) {
			app.createProject("Project #" + i);
		}

		// Attempt to add the 10000th project
		app.createProject("Project #10000");
	}
}