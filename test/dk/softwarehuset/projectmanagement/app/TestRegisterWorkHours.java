package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.util.SampleDataSetupWithProjects;

public class TestRegisterWorkHours extends SampleDataSetupWithProjects {
	
	@Test
	public void testRegisterWorkHours() throws WrongCredentialsException, NonUniqueIdentifierException {
		String id = "120001";
		
		// Sign in as employee
		app.signIn("ABCD");
		
		Employee employee = app.getEmployeeById("ABCD");
		Project project = app.getProjectById(id);
		
		// Add employee to project
		project.addEmployee(employee);
		
		Activity laughingCourse = app.createActivity(employee, "Laughing Course");
		Activity designPhase = app.createActivity(project, "Design Phase");
		
		// Add activities
		employee.addActivity(laughingCourse);
		project.addActivity(designPhase);
		
		// Register work hours
		employee.registerTime(laughingCourse, 120);
		employee.registerTime(designPhase, 120);
		
		// Check that it was registered
		assertEquals(120, laughingCourse.getTotalRegisteredTime());
		assertEquals(120, designPhase.getTotalRegisteredTime());
	}
	
	@Test
	public void testRegisterWorkHoursMultiple() throws WrongCredentialsException, NonUniqueIdentifierException {
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
		
		// Add activities
		employee.addActivity(laughingCourse);
		project.addActivity(designPhase);
		
		// Register work hours
		employee.registerTime(laughingCourse, 120);
		employee.registerTime(designPhase, 120);
		employee.registerTime(breakPhase, 120);
		employee.registerTime(lunchPhase, 60);
		employee.registerTime(sleepPhase, 90);
		
		// Check that it was registered
		assertEquals(120+120+120+60+90, employee.getTotalRegisteredTime());
		assertEquals(120+120+60+90, project.getTotalRegisteredTime());
		
		// add extra time
		employee.registerTime(laughingCourse, 120);
		employee.registerTime(designPhase, 270);
		assertEquals(120+120+120+60+90+120, employee.getTotalRegisteredTime());
		assertEquals(120+120+60+90+270, project.getTotalRegisteredTime());
		
	}

}
