package dk.softwarehuset.projectmanagement.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.app.DateServer;
import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.TooManyProjectsException;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;

@RunWith(value = Parameterized.class)
public class SystestRegisterTimeWithActivity {
	private Application app = new Application();
	private ApplicationUI appUI = new ApplicationUI(app);
	private ApplicationUITester appUITester = new ApplicationUITester(appUI);

	private int registeredTime;

	public SystestRegisterTimeWithActivity(int registeredTime) {
		this.registeredTime = registeredTime;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 0 }, { 24 * 60 } };
		return Arrays.asList(data);
	}

	@Before
	public void setup() throws IOException, PermissionDeniedException, InvalidArgumentException, TooManyProjectsException {
		// Set date
		DateServer dateServer = mock(DateServer.class);
		app.setDateServer(dateServer);
		LocalDate date = new LocalDate(2015, 12, 31);
		when(dateServer.getDate()).thenReturn(date);

		// Sample data
		app.signIn("ZZZZ");
		Employee employee = app.createEmployee("ABCD", "Alpha Bravo Charlie Delta");
		Project project = app.createProject("Whatever");
		Activity activity = app.createActivity(project, "Slack");
		app.signOut();

		project.addEmployee(employee);
		employee.registerTime(activity, registeredTime);

		// Sign in and prepare to register time
		appUITester.selectOption("Sign in").write("ABCD").expect("You signed in as \"Alpha Bravo Charlie Delta\".");
		appUITester.selectOption("Register work hours").expectNothing();
		appUITester.selectOption("Project activities").expectNothing();
		appUITester.selectOption("[150001] Whatever").expectNothing();
		appUITester.selectOption("Slack").expectNothing();

		appUITester.expect(
				"Time spent on activity \"Slack\" today: " + registeredTime + " min",
				"Register time (in minutes): "
				);
	}

	@Test
	public void testAandF() throws IOException {
		appUITester.write("").expect("Invalid duration. Try again.");
	}

	@Test
	public void testBandG() throws IOException {
		appUITester.write("abc").expect("Invalid duration. Try again.");
	}

	@Test
	public void testCandH() throws IOException {
		appUITester.write("-1").expect("Invalid duration. Try again.");
	}

	@Test
	public void testD1andI1() throws IOException {
		appUITester.write("0").expect("Time spent on activity \"Slack\" today: " + registeredTime + " min");
	}

	@Test
	public void testD2andI2() throws IOException {
		if (registeredTime == 0) {
			appUITester.write("1440").expect("Time spent on activity \"Slack\" today: 1440 min");
		} else if (registeredTime == 1441) {
			appUITester.write("1440").expect("There's only 24 hours (1440 minutes) in a day. Try again.");
		}
	}

	@Test
	public void testEandJ() throws IOException {
		appUITester.write("1441").expect("There's only 24 hours (1440 minutes) in a day. Try again.");
	}
}