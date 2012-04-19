package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestCreateActivity extends SampleDataSetupWithProjects {
	@Test
	public void testCreateActivityOnProject() {
		Project project = app.getProjectById("120001");
		String name = "Design phase";

		// Check no activies exist on project
		assertEquals(0, project.getActivities().size());

		// Create activity on project
		Activity activity = app.createActivity(project, name);

		// Check activity exists on project
		assertEquals(1, project.getActivities().size());
		assertEquals(activity, project.getActivities().get(0));
	}

	@Test
	public void testCreateActivityOnEmployee() {
		Employee employee = app.getEmployeeById("ABCD");
		String name = "Some course";

		// Check no activies exist on employee
		assertEquals(0, employee.getActivities().size());

		// Create activity on employee
		Activity activity = app.createActivity(employee, name);

		// Check activity exists on project
		assertEquals(1, employee.getActivities().size());
		assertEquals(activity, employee.getActivities().get(0));
	}
}