package dk.softwarehuset.projectmanagement.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.joda.time.LocalDate;
import org.junit.Before;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.DateServer;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;

public class SampleDataSetup {
	protected Application app = new Application();

	@Before
	public void setup() throws InvalidArgumentException, PermissionDeniedException {
		// Set date
		DateServer dateServer = mock(DateServer.class);
		app.setDateServer(dateServer);
		LocalDate date = new LocalDate(2012, 3, 26);
		when(dateServer.getDate()).thenReturn(date);

		// Sign in as administrator
		app.signIn("ZZZZ");

		// Add employees
		app.createEmployee("ABCD", "Alpha Bravo Charlie Delta");
		app.createEmployee("EFGH", "Echo Foxtrot Golf Hotel");
		app.createEmployee("IJKL", "India Juliet Kilo Lima");

		// Sign out
		app.signOut();
	}
}