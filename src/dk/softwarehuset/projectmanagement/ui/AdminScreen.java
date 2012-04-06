package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public class AdminScreen extends MenuListScreen {
	private static final String[] OPTIONS = new String[] {
			"Sign out",
			"Create employee"
	};

	public AdminScreen(ApplicationUI appUI) {
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
		} else if (option.equals("Create employee")) {
			appUI.setScreen(new CreateEmployeeIdScreen(appUI));
		}
	}
}