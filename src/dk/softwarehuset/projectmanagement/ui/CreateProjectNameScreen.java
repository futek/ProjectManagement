package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.TooManyProjectsException;

public class CreateProjectNameScreen extends PromptScreen {
	private static String PROMPT = "New project name";

	public CreateProjectNameScreen(ApplicationUI appUI) {
		super(appUI, PROMPT);
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		String name = input.trim();

		Project project = new Project(name);

		try {
			appUI.getApp().addProject(project);
		} catch (TooManyProjectsException e) {
			out.println(e.getMessage() + ".");
			appUI.setScreen(new EmployeeScreen(appUI));
			return;
		} catch (PermissionDeniedException e) {
			out.println("Not signed in.");
			appUI.setScreen(new StartScreen(appUI));
			return;
		}

		out.printf("Project \"%s\" created.\n", project.getName());
		appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));
	}
}