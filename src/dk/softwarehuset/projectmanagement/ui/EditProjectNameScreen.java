package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectNameScreen extends PromptScreen {
	private static final String TEXT = "New project name";

	private Project project;

	public EditProjectNameScreen(ApplicationUI appUI, Project project) {
		super(appUI);

		this.project = project;
	}

	@Override
	public String getText() {
		return TEXT;
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