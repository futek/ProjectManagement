package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectNameScreen extends PromptScreen {
	private static String PROMPT = "New project name";

	private Project project;

	public EditProjectNameScreen(ApplicationUI appUI, Project project) {
		super(appUI, PROMPT);

		this.project = project;
	}

	@Override
	public void print(PrintWriter out) {
		out.printf("Old project name: %s\n", project.getName());
		super.print(out);
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		String name = input.trim();
		project.setName(name);

		appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));
	}
}