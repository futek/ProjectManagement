package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestJoinAndLeaveProject extends SampleDataSetupWithProjects {

	@Test
	public void testJoinProject() throws InvalidArgumentException {
		String id = "120001";
		// check no employees on project
		assertEquals(0, app.getProjectById(id).getEmployees().size());

		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById(id);

		// add employee to project
		project.addEmployee(employee);

		// check employee is on project
		assertEquals(1, app.getProjectById(id).getEmployees().size());
		assertEquals(employee, app.getProjectById(id).getEmployees().get(0));
	}

	@Test
	public void testLeaveProject() throws InvalidArgumentException {
		String id = "120001";
		// check no employees on project
		assertEquals(0, app.getProjectById(id).getEmployees().size());

		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById(id);

		// add employee to project
		project.addEmployee(employee);

		// check employee is on project
		assertEquals(1, app.getProjectById(id).getEmployees().size());
		assertEquals(employee, app.getProjectById(id).getEmployees().get(0));

		// remove employee from project
		project.removeEmployee(employee);

		// check no employee is on the project
		assertEquals(0, app.getProjectById(id).getEmployees().size());
	}

	@Test
	public void testLeaveProjectAsProjectLeader() throws InvalidArgumentException {
		String id = "120001";
		// check no employees on project
		assertEquals(0, app.getProjectById(id).getEmployees().size());

		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById(id);

		// add employee to project
		project.addEmployee(employee);

		// check employee is on project
		assertEquals(1, app.getProjectById(id).getEmployees().size());
		assertEquals(employee, app.getProjectById(id).getEmployees().get(0));

		// Check no project leader exists
		assertNull(project.getProjectLeader());

		// Set project leader
		project.setProjectLeader(employee);

		// Check project leader is employee
		assertEquals(employee, project.getProjectLeader());

		// remove employee from project
		project.removeEmployee(employee);

		// Check project has no leader
		assertNull(project.getProjectLeader());

		// check no employee is on the project
		assertEquals(0, app.getProjectById(id).getEmployees().size());
	}

	@Test(expected = InvalidArgumentException.class)
	public void testJoinProjectTwiceFail() throws InvalidArgumentException {
		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById("120001");

		// Add employee twice
		project.addEmployee(employee);
		project.addEmployee(employee);
	}
}
