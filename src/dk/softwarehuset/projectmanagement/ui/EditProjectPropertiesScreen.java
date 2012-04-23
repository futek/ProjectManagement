package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectPropertiesScreen extends MenuListScreen {
	private static final String[] OPTIONS = {
			"Back",
			"Edit name",
			"Edit start date",
			"Edit end date"
	};

	private Project project;
	private Screen source;

	public EditProjectPropertiesScreen(ApplicationUI appUI, Screen source, Project project) {
		super(appUI);

		this.source = source;
		this.project = project;
	}

	@Override
	public String[] getOptions() {
		return OPTIONS;
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Back")) {
			appUI.setScreen(source);
		} else if (option.equals("Edit name")) {
			appUI.setScreen(new EditProjectNameScreen(appUI, project));
		} else if (option.equals("Edit start date")) {
			appUI.setScreen(new EditProjectStartYearScreen(appUI, this, project));
		} else if (option.equals("Edit end date")) {
			appUI.setScreen(new EditProjectEndYearScreen(appUI, this, project));
		}
	}
}