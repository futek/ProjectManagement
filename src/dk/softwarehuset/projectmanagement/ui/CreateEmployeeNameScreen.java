package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;

public class CreateEmployeeNameScreen extends PromptScreen {
	private static final String TEXT = "New employee name";

	private String id;

	public CreateEmployeeNameScreen(ApplicationUI appUI, String id) {
		super(appUI);

		this.id = id;
	}

	@Override
	public String getText() {
		return TEXT;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		String name = input.trim();
		Employee employee;

		try {
			employee = appUI.getApp().createEmployee(id, name);
		} catch (InvalidArgumentException e) {
			out.println(e.getMessage() + ".");
			appUI.setScreen(new MainScreen(appUI));
			return;
		} catch (PermissionDeniedException e) {
			out.println(e.getMessage() + ".");
			appUI.setScreen(new StartScreen(appUI));
			return;
		}

		out.printf("Employee \"%s\" created.%n", employee.getName());
		appUI.setScreen(new MainScreen(appUI));
	}
}