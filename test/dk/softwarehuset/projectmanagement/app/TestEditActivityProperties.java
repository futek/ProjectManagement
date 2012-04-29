package dk.softwarehuset.projectmanagement.app;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestEditActivityProperties extends SampleDataSetupWithProjects {
	private Project project;
	private Activity activity;

	@Before
	public void setupActivityOnProject() throws IOException, InvalidArgumentException {
		// Sign in as an employee
		app.signIn("IJKL");

		// Find project
		project = app.getProjectById("120002");

		// Join and register project leader
		project.addEmployee(app.getCurrentEmployee());
		project.setProjectLeader(app.getCurrentEmployee());

		// Create activity
		activity = app.createActivity(project, "Design Phase");
	}

	@Test
	public void testEditActivityDates() throws InvalidArgumentException {
		// Make sure no exceptions are thrown with valid dates
		activity.setStartDate(2012, 4);
		activity.setEndDate(2012, 6);
		activity.setEndDate(2013, 3);
		activity.setStartDate(2012, 1);
	}
}