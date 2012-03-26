package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;

public class CreateEmployeeNameScreen extends PromptScreen {
	private static String PROMPT = "New employee name";

	private String id;

	public CreateEmployeeNameScreen(ApplicationUI appUI, String id) {
		super(appUI, PROMPT);

		this.id = id;
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		String name = input.trim();

		Employee employee = new Employee(id, name);

		try {
			appUI.getApplication().addEmployee(employee);
		} catch (NonUniqueIdentifierException e) {
			out.println("Employee id taken.");
			appUI.setScreen(new AdminScreen(appUI));
			return false;
		} catch (PermissionDeniedException e) {
			out.println("Not admin.");
			appUI.setScreen(new StartScreen(appUI));
			return false;
		}

		appUI.setScreen(new AdminScreen(appUI));

		return false;
	}
}