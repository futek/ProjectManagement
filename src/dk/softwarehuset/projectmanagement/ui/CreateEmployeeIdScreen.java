package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

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

		if (appUI.getApp().getEmployees().containsKey(id)) {
			out.println("Employee id taken.");
			appUI.setScreen(new AdminScreen(appUI));
			return;
		}

		appUI.setScreen(new CreateEmployeeNameScreen(appUI, id));
	}
}