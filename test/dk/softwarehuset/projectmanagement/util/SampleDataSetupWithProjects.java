package dk.softwarehuset.projectmanagement.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.DateServer;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
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
		app.createEmployee("ABCD", "Alpha Bravo Charlie Delta");
		app.createEmployee("EFGH", "Echo Foxtrot Golf Hotel");
		app.createEmployee("IJKL", "India Juliet Kilo Lima");

		// Add projects
		app.createProject("Hello World!");
		app.createProject("Goodbye World!");
		app.createProject("Good morning World!");
		app.createProject("Good afternoon World!");
		app.createProject("Good evening World!");

		// Sign out
		app.signOut();
	}
}