package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;

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
		String id = input.trim();

		try {
			appUI.getApp().signIn(id);
		} catch (InvalidArgumentException e) {
			out.println(e.getMessage() + ".");
			appUI.setScreen(new StartScreen(appUI));
			return;
		}

		Employee currentEmployee = appUI.getApp().getCurrentEmployee();

		appUI.setScreen(new MainScreen(appUI));

		out.println("You signed in as \"" + currentEmployee.getName() + "\".");
	}
}