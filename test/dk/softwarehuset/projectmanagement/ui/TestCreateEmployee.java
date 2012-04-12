package dk.softwarehuset.projectmanagement.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.WrongCredentialsException;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;

public class TestCreateEmployee {
	private Application app = new Application();
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testCreateEmployee() throws WrongCredentialsException, PermissionDeniedException, NonUniqueIdentifierException, IOException {
		// Check no employees exist
		assertTrue(app.getEmployees().size() == 1);

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee with admin rights
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("ZZZZ").expect("You signed in as \"Administrator\".");

		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());

		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write(id).expectNothing();
		appUITester.expect("New employee name: ").write(name).expectNothing();

		// Check employee was added
		assertTrue(app.getEmployees().size() == 2);
		assertNotNull(app.getEmployeeById(id));
		assertEquals(app.getEmployeeById(id).getId(), id);
		assertEquals(app.getEmployeeById(id).getName(), name);

		// Sign out
		appUITester.selectOption("Sign out").expect("You signed out.");

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Exit
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testCreateEmployeeFailNonUniqueIdentifier() throws IOException {
		// Check no employees exist
		assertTrue(app.getEmployees().size() == 1);

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Sign in as an employee with admin rights
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");

		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());

		// Add new employee
		String id1 = "ABCD";
		String name1 = "Alpha Bravo Charlie Delta";
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write(id1).expectNothing();
		appUITester.expect("New employee name: ").write(name1).expectNothing();

		// Check employee was added
		assertTrue(app.getEmployees().size() == 2);
		assertNotNull(app.getEmployeeById(id1));
		assertEquals(app.getEmployeeById(id1).getId(), id1);
		assertEquals(app.getEmployeeById(id1).getName(), name1);

		// Add another employee with same id
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write(id1).expect("Employee id taken.");

		// Check employee not added
		assertTrue(app.getEmployees().size() == 2);

		// Sign out
		appUITester.selectOption("Sign out").expect("You signed out.");

		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());

		// Exit
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}
}