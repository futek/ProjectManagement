package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCreateEmployee {
	private Application app = new Application();

	@Test(expected = InvalidArgumentException.class)
	public void testSignInFailedWrongUsername() throws InvalidArgumentException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in with unknown id
		app.signIn("ABCD");
	}

	@Test
	public void testAdminSignInAndOut() throws InvalidArgumentException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee with admin rights
		app.signIn("ZZZZ");

		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());

		// Sign out
		app.signOut();

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
	}

	@Test
	public void testCreateEmployee() throws InvalidArgumentException, PermissionDeniedException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee with admin rights
		app.signIn("ZZZZ");

		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());

		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		app.createEmployee(id, name);

		// Check employee was added
		assertNotNull(app.getEmployeeById(id));

		// Sign out
		app.signOut();

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
	}

	@Test(expected = PermissionDeniedException.class)
	public void testCreateEmployeeFailNotSignedIn() throws InvalidArgumentException, PermissionDeniedException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		app.createEmployee(id, name);
	}

	@Test(expected = PermissionDeniedException.class)
	public void testCreateEmployeeFailNotAdmin() throws InvalidArgumentException, PermissionDeniedException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee with admin rights
		app.signIn("ZZZZ");

		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());

		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		app.createEmployee(id, name);

		// Check employee was added
		assertNotNull(app.getEmployeeById(id));

		// Sign out
		app.signOut();

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as new employee
		app.signIn("ABCD");

		// Check employee is signed in and is not admin
		assertNotNull(app.getCurrentEmployee());
		assertFalse(app.getCurrentEmployee().isAdmin());

		// Add new employee
		String id2 = "EFGH";
		String name2 = "Echo Foxtrot Golf Hotel";
		app.createEmployee(id2, name2);
	}

	@Test(expected = InvalidArgumentException.class)
	public void testCreateEmployeeFailNonUniqueIdentifier() throws InvalidArgumentException, PermissionDeniedException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee with admin rights
		app.signIn("ZZZZ");

		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());

		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		app.createEmployee(id, name);

		// Check employee was added
		assertNotNull(app.getEmployeeById(id));

		// Add new employee
		String id2 = "ABCD";
		String name2 = "Echo Foxtrot Golf Hotel";
		app.createEmployee(id2, name2);
	}

	@Test
	public void testSearchForNonexistentEmployee() {
		// Attempt to get a employee from a nonexistent id
		Employee employee = app.getEmployeeById("ROFL");

		// Check no employee was found
		assertNull(employee);
	}
}