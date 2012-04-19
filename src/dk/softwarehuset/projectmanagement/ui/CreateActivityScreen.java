package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Project;

public class CreateActivityScreen extends PromptScreen {
	private static final String TEXT = "Activity name";

	private Screen source;
	private Project project;

	public CreateActivityScreen(ApplicationUI appUI, Screen source, Project project) {
		super(appUI);

		this.source = source;
		this.project = project;
	}

	public CreateActivityScreen(ApplicationUI appUI, Screen source) {
		super(appUI);

		this.source = source;
	}

	@Override
	public String getText() {
		return TEXT;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		Activity activity;
		String name = input.trim();

		if (project != null) {
			// Add activity to project
			activity = appUI.getApp().createActivity(project, name);
			out.printf("Activity \"%s\" created on project \"%s\".%n", name, project.getName());
		} else {
			activity = appUI.getApp().createActivity(appUI.getApp().getCurrentEmployee(), name);
			out.printf("Activity \"%s\" created.%n", name);
		}

		appUI.setScreen(new ViewActivityScreen(appUI, source, activity));
	}
}