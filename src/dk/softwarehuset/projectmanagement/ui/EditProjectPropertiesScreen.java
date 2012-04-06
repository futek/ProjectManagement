package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectPropertiesScreen extends MenuListScreen {
	private static String[] OPTIONS = {
			"Exit",
			"Edit name"
	};

	private Project project;

	public EditProjectPropertiesScreen(ApplicationUI appUI, Project project) {
		super(appUI, OPTIONS);
		this.project = project;
	}

	@Override
	public void optionSelected(String option, PrintWriter out) {
		if (option.equals("Exit")) {
			appUI.setScreen(new EmployeeScreen(appUI));
		} else if (option.equals("Edit name")) {
			appUI.setScreen(new EditProjectNameScreen(appUI, project));
		}
	}
}