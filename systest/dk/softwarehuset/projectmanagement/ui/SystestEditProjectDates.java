package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;

public class SystestEditProjectDates {
	Application app = new Application();
	ApplicationUI appUI = new ApplicationUI(app);
	ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Before
	public void setup() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");
		appUITester.selectOption("Create project").expectNothing();
		appUITester.expect("New project name: ").write("Whatever").expect("Project \"Whatever\" created.");
	}

	@Test
	public void testA() throws IOException {
		appUITester.selectOption("Edit start date").expectNothing();
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("16").expectNothing();
		appUITester.selectOption("Edit end date").expectNothing();
		appUITester.expect("New end week year: ").write("2011").expectNothing();
		appUITester.expect("New end week number: ").write("36").expect("End date before start date. Try again.");
	}

	@Test
	public void testB() throws IOException {
		appUITester.selectOption("Edit start date").expectNothing();
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("16").expectNothing();
		appUITester.selectOption("Edit end date").expectNothing();
		appUITester.expect("New end week year: ").write("2012").expectNothing();
		appUITester.expect("New end week number: ").write("16").expectNothing(); // Success!
	}

	@Test
	public void testC() throws IOException {
		appUITester.selectOption("Edit start date").expectNothing();
		appUITester.expect("New start week year: ").write("2012").expectNothing();
		appUITester.expect("New start week number: ").write("16").expectNothing();
		appUITester.selectOption("Edit end date").expectNothing();
		appUITester.expect("New end week year: ").write("2013").expectNothing();
		appUITester.expect("New end week number: ").write("8").expectNothing(); // Success!
	}
}