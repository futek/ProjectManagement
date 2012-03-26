package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectPropertiesScreen extends Screen {
	private Project project;
	
	public EditProjectPropertiesScreen(ApplicationUI appUI, Project project) {
		super(appUI);
		this.project = project;
	}

	@Override
	public void printMenu(PrintWriter out) {
		out.print("0) Exit\n" +
							"1) Edit name\n" +
							"> ");
		out.flush();
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		if (input.equals("0")) {
			out.println("");
			appUI.setScreen(new EmployeeScreen(appUI));
		} else if (input.equals("1")) {
			out.println("");
			appUI.setScreen(new EditProjectNameScreen(appUI, project));
		} else {
			out.println("Wrong selection.");
		}

		return false;
	}
}