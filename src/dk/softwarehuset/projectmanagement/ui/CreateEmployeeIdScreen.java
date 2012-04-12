package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Employee;

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
		String id = input.trim().toUpperCase();

		for (Employee employee : appUI.getApp().getEmployees()) {
			if (id.equals(employee.getId())) {
				out.println("Employee id taken.");
				appUI.setScreen(new MainScreen(appUI));
				return;
			}
		}

		appUI.setScreen(new CreateEmployeeNameScreen(appUI, id));
	}
}