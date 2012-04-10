package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectPropertiesScreen extends MenuListScreen {
	private static final String[] OPTIONS = {
			"Exit",
			"Edit name"
	};

	private Project project;

	public EditProjectPropertiesScreen(ApplicationUI appUI, Project project) {
		super(appUI);

		this.project = project;
	}

	@Override
	public String[] getOptions() {
		return OPTIONS;
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Exit")) {
			appUI.setScreen(new MainScreen(appUI));
		} else if (option.equals("Edit name")) {
			appUI.setScreen(new EditProjectNameScreen(appUI, project));
		}
	}
}