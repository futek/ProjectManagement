package dk.softwarehuset.projectmanagement.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.DateServer;
import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.TooManyProjectsException;
import dk.softwarehuset.projectmanagement.app.WrongCredentialsException;

public class SampleDataSetupWithProjects {
	protected Application app = new Application();
	
	@Before
	public void setup() throws WrongCredentialsException, PermissionDeniedException, NonUniqueIdentifierException, TooManyProjectsException {
		// Set date
		DateServer dateServer = mock(DateServer.class);
		app.setDateServer(dateServer);
		Calendar date = new GregorianCalendar(2012, Calendar.MARCH, 26);
		when(dateServer.getDate()).thenReturn(date);
		
		// Sign in as administrator
		app.signIn("ZZZZ");
		
		// Add employees
		Employee employee1 = new Employee("ABCD", "Alpha Bravo Charlie Delta");
		Employee employee2 = new Employee("EFGH", "Echo Foxtrot Golf Hotel");
		Employee employee3 = new Employee("IJKL", "India Juliet Kilo Lima");
		app.addEmployee(employee1);
		app.addEmployee(employee2);
		app.addEmployee(employee3);
		
		
		Project project1 = new Project("Hello World!");
		Project project2 = new Project("Goodbye World!");
		Project project3 = new Project("Good morning World!");
		Project project4 = new Project("Good afternoon World!");
		Project project5 = new Project("Good evening World!");
		
		app.addProject(project1);
		app.addProject(project2);
		app.addProject(project3);
		app.addProject(project4);
		app.addProject(project5);
		
		// Sign out
		app.signOut();
	}
}