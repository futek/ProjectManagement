package dk.softwarehuset.projectmanagement.ui;

import java.io.IOException;

import org.junit.Test;

import dk.softwarehuset.projectmanagement.app.Application;
import dk.softwarehuset.projectmanagement.util.ApplicationUITester;

public class CreateEmployee {
	Application app = new Application();
	ApplicationUI appUI = new ApplicationUI(app);
	ApplicationUITester appUITester = new ApplicationUITester(appUI);

	@Test
	public void testA() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("").expect("No id given.");
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testB() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzz").expect("Id too short.");
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testC() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write("").expect("No id given.");
		appUITester.selectOption("Sign out").expect("You signed out.");
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testD() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write("zzz").expect("Id too short.");
		appUITester.selectOption("Sign out").expect("You signed out.");
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testE() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write("zzzz").expect("Id already taken.");
		appUITester.selectOption("Sign out").expect("You signed out.");
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testF() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write("abcd").expectNothing();
		appUITester.expect("New employee name: ").write("").expect("No name given.");
		appUITester.selectOption("Sign out").expect("You signed out.");
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testG() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write("abcd").expectNothing();
		appUITester.expect("New employee name: ").write("Elton John").expect("Employee \"Elton John\" created.");
		appUITester.selectOption("Sign out").expect("You signed out.");
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testH() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzz").expect("You signed in as \"Administrator\".");
		appUITester.selectOption("Create employee").expectNothing();
		appUITester.expect("New employee id: ").write("abcde").expect("Id too long.");
		appUITester.selectOption("Sign out").expect("You signed out.");
		appUITester.selectOption("Exit").expect("Exited.").expectExit();
	}

	@Test
	public void testI() throws IOException {
		appUITester.selectOption("Sign in").expectNothing();
		appUITester.expect("Employee id: ").write("zzzzz").expect("Id too long.");
	}
}