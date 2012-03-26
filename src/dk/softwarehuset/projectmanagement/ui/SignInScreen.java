package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.WrongCredentialsException;

public class SignInScreen extends PromptScreen {
	private static String PROMPT = "Employee id";

	public SignInScreen(ApplicationUI appUI) {
		super(appUI, PROMPT);
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		String id = input.trim().toUpperCase();

		try {
			appUI.getApplication().SignIn(id);
		} catch (WrongCredentialsException e) {
			out.println("Wrong credentials.");
			appUI.setScreen(new StartScreen(appUI));
			return false;
		}

		Employee currentEmployee = appUI.getApplication().getCurrentEmployee();

		if (currentEmployee.isAdmin()) {
			appUI.setScreen(new AdminScreen(appUI));
		} else {
			appUI.setScreen(new EmployeeScreen(appUI));
		}

		out.println("You signed in as \"" + currentEmployee.getName() + "\".");

		return false;
	}
}