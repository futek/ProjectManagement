package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestRegisterWorkHours extends SampleDataSetupWithProjects {

	@Test
	public void testRegisterWorkHours() throws InvalidArgumentException {
		String id = "120001";

		// Sign in as employee
		app.signIn("ABCD");

		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById(id);

		// Add employee to project
		project.addEmployee(employee);

		// Create activities on project and employee
		Activity laughingCourse = app.createActivity(employee, "Laughing Course");
		Activity designPhase = app.createActivity(project, "Design Phase");

		// Register work hours
		employee.registerTime(laughingCourse, 120);
		employee.registerTime(designPhase, 120);

		// Check that it was registered
		assertEquals(120, laughingCourse.getTotalRegisteredTime());
		assertEquals(120, designPhase.getTotalRegisteredTime());
	}

	@Test
	public void testRegisterWorkHoursMultiple() throws InvalidArgumentException {
		String id = "120001";

		// Sign in as employee
		app.signIn("ABCD");

		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById(id);

		// Add employee to project
		project.addEmployee(employee);

		Activity laughingCourse = app.createActivity(employee, "Laughing Course");
		Activity designPhase = app.createActivity(project, "Design Phase");
		Activity breakPhase = app.createActivity(project, "Break Phase");
		Activity lunchPhase = app.createActivity(project, "Lunch Phase");
		Activity sleepPhase = app.createActivity(project, "Sleep Phase");

		assertEquals(0, employee.getTotalRegisteredTime());
		assertEquals(0, project.getTotalRegisteredTime());

		// Add project activities to employee
		employee.addActivity(designPhase);
		employee.addActivity(breakPhase);
		employee.addActivity(lunchPhase);
		employee.addActivity(sleepPhase);

		// Register work hours
		employee.registerTime(laughingCourse, 120);
		employee.registerTime(designPhase, 120);
		employee.registerTime(breakPhase, 120);
		employee.registerTime(lunchPhase, 60);
		employee.registerTime(sleepPhase, 90);

		// Check that it was registered
		assertEquals(120 + 120 + 120 + 60 + 90, employee.getTotalRegisteredTime());
		assertEquals(120 + 120 + 60 + 90, project.getTotalRegisteredTime());

		// add extra time
		employee.registerTime(laughingCourse, 120);
		employee.registerTime(designPhase, 270);
		assertEquals(120 + 120 + 120 + 60 + 90 + 120 + 270, employee.getTotalRegisteredTime());
		assertEquals(120 + 120 + 60 + 90 + 270, project.getTotalRegisteredTime());

	}

	@Test(expected = InvalidArgumentException.class)
	public void testSetRegisteredTimeFailNegativeDuration() throws InvalidArgumentException {
		// Sign in as employee
		app.signIn("ABCD");

		// Setup employee and project
		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById("120001");

		// Add employee to project
		project.addEmployee(employee);

		// Create activity on project
		Activity activity = app.createActivity(project, "Design Phase");

		// Register negative duration directly
		activity.setRegisteredTime(employee, app.getDate(), -30);
	}
}
