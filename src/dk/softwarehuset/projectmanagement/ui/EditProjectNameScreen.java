package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectNameScreen extends Screen {
	private Project project;

	public EditProjectNameScreen(ApplicationUI appUI, Project project) {
		super(appUI);
		this.project = project;
	}

	@Override
	public void printMenu(PrintWriter out) {
		out.println("Old project name: " + project.getName());
		out.print("New project name: ");
		out.flush();
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		String name = input.trim();
		project.setName(name);

		appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));

		return false;
	}
}