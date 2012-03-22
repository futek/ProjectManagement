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
import dk.softwarehuset.projectmanagement.util.UITestUtil;

public class TestCreateEmployee {
	private Application app = new Application();
	private ApplicationUI appUI = new ApplicationUI(app);
	
	@Test
	public void testCreateEmployee() throws WrongCredentialsException, PermissionDeniedException, NonUniqueIdentifierException, IOException {
		// Check no employees exist
		assertTrue(app.getEmployees().size() == 1);
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as an employee with admin rights
		UITestUtil.testScreenInteraction(appUI, "1) Sign In", "1", "", false);
		UITestUtil.testScreenInteraction(appUI, "Employee id: ", "ZZZZ", "You signed in as \"Administrator\".", false);
		
		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());
		
		// Add new employee
		String id = "ABCD";
		String name = "Alpha Bravo Charlie Delta";
		UITestUtil.testScreenInteraction(appUI, "1) Add Employee", "1", "", false);
		UITestUtil.testScreenInteraction(appUI, "New employee id: ", id, "", false);
		UITestUtil.testScreenInteraction(appUI, "New employee name: ", name, "", false);
		
		// Check employee was added
		assertTrue(app.getEmployees().size() == 2);
		assertTrue(app.getEmployees().containsKey(id));
		assertEquals(app.getEmployees().get(id).getId(), id);
		assertEquals(app.getEmployees().get(id).getName(), name);
		
		// Sign out
		UITestUtil.testScreenInteraction(appUI, "0) Sign Out", "0", "You signed out.", false);
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Exit
		UITestUtil.testScreenInteraction(appUI, "0) Exit", "0", "Exited.", true);
	}

	@Test
	public void testCreateEmployeeFailNonUniqueIdentifier() throws IOException {
		// Check no employees exist
		assertTrue(app.getEmployees().size() == 1);
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Sign in as an employee with admin rights
		UITestUtil.testScreenInteraction(appUI, "1) Sign In", "1", "", false);
		UITestUtil.testScreenInteraction(appUI, "Employee id: ", "ZZZZ", "You signed in as \"Administrator\".", false);
		
		// Check employee is signed in and is admin
		assertNotNull(app.getCurrentEmployee());
		assertTrue(app.getCurrentEmployee().isAdmin());
		
		// Add new employee
		String id1 = "ABCD";
		String name1 = "Alpha Bravo Charlie Delta";
		UITestUtil.testScreenInteraction(appUI, "1) Add Employee", "1", "", false);
		UITestUtil.testScreenInteraction(appUI, "New employee id: ", id1, "", false);
		UITestUtil.testScreenInteraction(appUI, "New employee name: ", name1, "", false);
		
		// Check employee was added
		assertTrue(app.getEmployees().size() == 2);
		assertTrue(app.getEmployees().containsKey(id1));
		assertEquals(app.getEmployees().get(id1).getId(), id1);
		assertEquals(app.getEmployees().get(id1).getName(), name1);
		
		// Add another employee with same id
		UITestUtil.testScreenInteraction(appUI, "1) Add Employee", "1", "", false);
		UITestUtil.testScreenInteraction(appUI, "New employee id: ", id1, "Employee id taken.", false);
		
		// Check employee not added
		assertTrue(app.getEmployees().size() == 2);
		
		// Sign out
		UITestUtil.testScreenInteraction(appUI, "0) Sign Out", "0", "You signed out.", false);
		
		// Check nobody is signed in
		assertNull(app.getCurrentEmployee());
		
		// Exit
		UITestUtil.testScreenInteraction(appUI, "0) Exit", "0", "Exited.", true);
	}
}