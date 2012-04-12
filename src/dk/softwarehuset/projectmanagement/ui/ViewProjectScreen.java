package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.Project;

public class ViewProjectScreen extends MenuListScreen {
	private Screen source;
	private Project project;

	public ViewProjectScreen(ApplicationUI appUI, Screen source, Project project) {
		super(appUI);

		this.source = source;
		this.project = project;
	}

	@Override
	public String[] getOptions() {
		List<String> options = new ArrayList<String>();

		options.add("Back");

		boolean inProject = false;
		for (Employee employee : project.getEmployees()) {
			if (appUI.getApp().getCurrentEmployee().equals(employee)) {
				inProject = true;
				break;
			}
		}

		if (inProject) {
			options.add("Leave project");
		} else {
			options.add("Join project");
		}

		String[] array = options.toArray(new String[0]);

		return array;
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Back")) {
			appUI.setScreen(source);
		} else if (option.equals("Join project")) {
			try {
				project.addEmployee(appUI.getApp().getCurrentEmployee());
				out.println("You've joined the project \"" + project.getName() + "\".");
			} catch (NonUniqueIdentifierException e) {
				out.println(e.getMessage() + ".");
			}
		} else if (option.equals("Leave project")) {
			project.removeEmployee(appUI.getApp().getCurrentEmployee());
			out.println("You've left the project \"" + project.getName() + "\".");
		}
	}
}