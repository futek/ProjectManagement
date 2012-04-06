package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public class EmployeeScreen extends MenuListScreen {
	private static final String[] OPTIONS = new String[] {
			"Sign out",
			"Create project",
			"Browse all projects"
	};

	public EmployeeScreen(ApplicationUI appUI) {
		super(appUI);
	}

	@Override
	public String[] getOptions() {
		return OPTIONS;
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Sign out")) {
			appUI.getApp().signOut();
			out.println("You signed out.");
			appUI.setScreen(new StartScreen(appUI));
		} else if (option.equals("Create project")) {
			appUI.setScreen(new CreateProjectNameScreen(appUI));
		} else if (option.equals("Browse all projects")) {
			appUI.setScreen(new BrowseAllProjectsScreen(appUI));
		}
	}
}