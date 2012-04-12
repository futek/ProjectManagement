package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.TooManyProjectsException;

public class CreateProjectNameScreen extends PromptScreen {
	private static final String TEXT = "New project name";

	public CreateProjectNameScreen(ApplicationUI appUI) {
		super(appUI);
	}

	@Override
	public String getText() {
		return TEXT;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		String name = input.trim();

		Project project = null;

		try {
			project = appUI.getApp().createProject(name);
		} catch (TooManyProjectsException e) {
			out.println(e.getMessage() + ".");
			appUI.setScreen(new MainScreen(appUI));
			return;
		} catch (PermissionDeniedException e) {
			out.println(e.getMessage() + ".");
			appUI.setScreen(new StartScreen(appUI));
			return;
		}

		out.printf("Project \"%s\" created.\n", project.getName());
		appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));
	}
}