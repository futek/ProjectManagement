package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.fail;

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

	public void testWeekOfWeekyear() throws InvalidArgumentException {
		try {
			activity.setStartDate(2014, 53); // No week 53 in 2014
			fail("Invalid week of week year");
		} catch (InvalidArgumentException e) {
		}

		activity.setStartDate(2015, 53);// Week 53 is in 2015

		try {
			activity.setStartDate(2016, 53);
			fail("Invalid week of week year"); // No week 53 in 2016
		} catch (InvalidArgumentException e) {
		}

		try {
			activity.setEndDate(2014, 53);
			fail("Invalid week of week year"); // No week 53 in 2014
		} catch (InvalidArgumentException e) {
		}

		activity.setEndDate(2015, 53); // Week 53 is in 2015

		try {
			activity.setEndDate(2016, 53);
			fail("Invalid week of week year"); // No week 53 in 2016
		} catch (InvalidArgumentException e) {
		}
	}
}