package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public class CreateEmployeeIdScreen extends Screen {
	public CreateEmployeeIdScreen(ApplicationUI appUI) {
		super(appUI);
	}

	@Override
	public void printMenu(PrintWriter out) {
		out.print("New employee id: ");
		out.flush();
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		String id = input.trim().toUpperCase();
		
		if (appUI.getApplication().getEmployees().containsKey(id)) {
			out.println("Employee id taken.");
			appUI.setScreen(new AdminScreen(appUI));
			return false;
		}
		
		out.println();
		appUI.setScreen(new CreateEmployeeNameScreen(appUI, id));
		
		return false;
	}
}