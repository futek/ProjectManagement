package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.WrongCredentialsException;

public class SignInScreen extends PromptScreen {
	private static final String TEXT = "Employee id";

	public SignInScreen(ApplicationUI appUI) {
		super(appUI);
	}

	@Override
	public String getText() {
		return TEXT;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		String id = input.trim().toUpperCase();

		try {
			appUI.getApp().signIn(id);
		} catch (WrongCredentialsException e) {
			out.println("Wrong credentials.");
			appUI.setScreen(new StartScreen(appUI));
			return;
		}

		Employee currentEmployee = appUI.getApp().getCurrentEmployee();

		if (currentEmployee.isAdmin()) {
			appUI.setScreen(new AdminScreen(appUI));
		} else {
			appUI.setScreen(new EmployeeScreen(appUI));
		}

		out.println("You signed in as \"" + currentEmployee.getName() + "\".");
	}
}