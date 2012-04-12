package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
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

		try {
			appUI.getApp().createEmployee(id, name);
		} catch (NonUniqueIdentifierException e) {
			out.println("Employee id taken.");
			appUI.setScreen(new MainScreen(appUI));
			return;
		} catch (PermissionDeniedException e) {
			out.println("Not admin.");
			appUI.setScreen(new StartScreen(appUI));
			return;
		}

		appUI.setScreen(new MainScreen(appUI));
	}
}