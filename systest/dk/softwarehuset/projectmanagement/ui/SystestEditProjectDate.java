package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;

public class SystestEditProjectDate {
	Application app = new Application();
	ApplicationUI appUI = new ApplicationUI(app);
	ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Before
	public void setup() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");
		appUITester.selectOption("Create project").expectNothing();
		appUITester.expect("New project name: ").write("Whatever").expect("Project \"Whatever\" created.");
		appUITester.selectOption("Edit start date").expectNothing();
	}

	@Test
	public void testA() throws IOException {
		appUITester.expect("New start week year: ").write("").expect("Invalid week year. Try again.");
	}

	@Test
	public void testB() throws IOException {
		appUITester.expect("New start week year: ").write("abc").expect("Invalid week year. Try again.");
	}

	@Test
	public void testC() throws IOException {
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("").expect("Invalid week number. Try again.");
	}

	@Test
	public void testD() throws IOException {
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("abc").expect("Invalid week number. Try again.");
	}

	@Test
	public void testE() throws IOException {
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("0").expect("Invalid week number. Try again.");
	}

	@Test
	public void testF1() throws IOException {
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("1").expectNothing(); // Success!
	}

	@Test
	public void testF2() throws IOException {
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("52").expectNothing(); // Success!
	}

	@Test
	public void testG() throws IOException {
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("53").expect("Invalid week number for week year 2012. Try again.");
	}

	@Test
	public void testH() throws IOException {
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("54").expect("Invalid week number. Try again.");
	}

	@Test
	public void testI() throws IOException {
		appUITester.expect("New start week year: ").write("2015").expectNothing();
		appUITester.expect("New start week number: ").write("").expect("Invalid week number. Try again.");
	}

	@Test
	public void testJ() throws IOException {
		appUITester.expect("New start week year: ").write("2015").expectNothing();
		appUITester.expect("New start week number: ").write("abc").expect("Invalid week number. Try again.");
	}

	@Test
	public void testK() throws IOException {
		appUITester.expect("New start week year: ").write("2015").expectNothing();
		appUITester.expect("New start week number: ").write("0").expect("Invalid week number. Try again.");
	}

	@Test
	public void testL1() throws IOException {
		appUITester.expect("New start week year: ").write("2015").expectNothing();
		appUITester.expect("New start week number: ").write("1").expectNothing(); // Success!
	}

	@Test
	public void testL2() throws IOException {
		appUITester.expect("New start week year: ").write("2015").expectNothing();
		appUITester.expect("New start week number: ").write("52").expectNothing(); // Success!
	}

	@Test
	public void testM() throws IOException {
		appUITester.expect("New start week year: ").write("2015").expectNothing();
		appUITester.expect("New start week number: ").write("53").expectNothing(); // Success!
	}

	@Test
	public void testN() throws IOException {
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("54").expect("Invalid week number. Try again.");
	}
}