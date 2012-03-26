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
	public boolean processInput(String input, PrintWriter out) {
		String name = input.trim();

		Project project = new Project(name);

		try {
			appUI.getApplication().addProject(project);
		} catch (TooManyProjectsException e) {
			out.println(e.getMessage() + ".");
			appUI.setScreen(new EmployeeScreen(appUI));
			return false;
		} catch (PermissionDeniedException e) {
			out.println("Not signed in.");
			appUI.setScreen(new StartScreen(appUI));
			return false;
		}

		out.printf("Project \"%s\" created.\n", project.getName());
		appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));

		return false;
	}
}