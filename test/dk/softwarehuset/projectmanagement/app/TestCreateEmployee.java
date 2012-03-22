package dk.softwarehuset.projectmanagement.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCreateEmployee {
	private Application app = new Application();
	
	@Test(expected=WrongCredentialsException.class)
	public void testSignInFailedWrongUsername() throws WrongCredentialsException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in with wrong credentials
		app.SignIn("ABCD");
	}
	
	@Test
	public void testAdminSignInAndOut() throws WrongCredentialsException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as an employee with admin rights
		app.SignIn("ZZZZ");
		
		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());
		
		// Sign out
		app.SignOut();
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
	}
	
	@Test
	public void testCreateEmployee() throws WrongCredentialsException, PermissionDeniedException, NonUniqueIdentifierException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as an employee with admin rights
		app.SignIn("ZZZZ");
		
		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());
		
		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		Employee employee = new Employee(id, name);
		app.addEmployee(employee);
		
		// Check employee was added
		assertTrue(app.getEmployees().containsKey(id));
		assertSame(app.getEmployees().get(id), employee);
		
		// Sign out
		app.SignOut();
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
	}
	
	@Test(expected=PermissionDeniedException.class)
	public void testCreateEmployeeFailNotSignedIn() throws PermissionDeniedException, NonUniqueIdentifierException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		Employee employee = new Employee(id, name);
		app.addEmployee(employee);
	}
	
	@Test(expected=PermissionDeniedException.class)
	public void testCreateEmployeeFailNotAdmin() throws WrongCredentialsException, PermissionDeniedException, NonUniqueIdentifierException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as an employee with admin rights
		app.SignIn("ZZZZ");
		
		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());
		
		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		Employee employee = new Employee(id, name);
		app.addEmployee(employee);
		
		// Check employee was added
		assertTrue(app.getEmployees().containsKey(id));
		assertSame(app.getEmployees().get(id), employee);
		
		// Sign out
		app.SignOut();
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as new employee
		app.SignIn("ABCD");
		
		// Check employee is signed in and is not admin
		assertNotNull(app.getCurrentEmployee());
		assertFalse(app.getCurrentEmployee().isAdmin());
		
		// Add new employee
		String id2 = "EFGH";
		String name2 = "Echo Foxtrot Golf Hotel";
		Employee employee2 = new Employee(id2, name2);
		app.addEmployee(employee2);
	}

	@Test(expected=NonUniqueIdentifierException.class)
	public void testCreateEmployeeFailNonUniqueIdentifier() throws WrongCredentialsException, PermissionDeniedException, NonUniqueIdentifierException {
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as an employee with admin rights
		app.SignIn("ZZZZ");
		
		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());
		
		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		Employee employee = new Employee(id, name);
		app.addEmployee(employee);
		
		// Check employee was added
		assertTrue(app.getEmployees().containsKey(id));
		assertSame(app.getEmployees().get(id), employee);
		
		// Add new employee
		String id2 = "ABCD";
		String name2 = "Echo Foxtrot Golf Hotel";
		Employee employee2 = new Employee(id2, name2);
		app.addEmployee(employee2);
	}
}