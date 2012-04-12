package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.*;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestRegisterAsProjectLeader extends SampleDataSetupWithProjects{

	@Test
	public void testRegisterAsProjectLeader() {
		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById("120004");
		
		// Check no project leader exists
		assertNull(project.getProjectLeader());
		
		// Set project leader
		project.setProjectLeader(employee);
		
		// Check project leader is employee
		assertEquals(employee, project.getProjectLeader());
		
		// Leave as project leader
		project.setProjectLeader(null);
		
		// Check no project leader exists
		assertNull(project.getProjectLeader());
	}

}
