package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;
import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.TooManyProjectsException;

public class CreateProjectNameScreen extends Screen {
	public CreateProjectNameScreen(ApplicationUI appUI) {
		super(appUI);
	}

	@Override
	public void printMenu(PrintWriter out) {
		out.print("New project name: ");
		out.flush();
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