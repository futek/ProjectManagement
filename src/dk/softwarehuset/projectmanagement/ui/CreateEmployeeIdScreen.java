package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;

public class CreateEmployeeIdScreen extends PromptScreen {
	private static final String TEXT = "New employee id";

	public CreateEmployeeIdScreen(ApplicationUI appUI) {
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
			Employee.validateId(id);
		} catch (InvalidArgumentException e) {
			out.println(e.getMessage() + ".");
			appUI.setScreen(new MainScreen(appUI));
			return;
		}

		if (appUI.getApp().getEmployeeById(id) != null) {
			out.println("Id already taken.");
			appUI.setScreen(new MainScreen(appUI));
			return;
		}

		appUI.setScreen(new CreateEmployeeNameScreen(appUI, id));
	}
}