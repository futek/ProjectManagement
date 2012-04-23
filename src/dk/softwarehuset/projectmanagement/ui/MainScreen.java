package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Project;

public class MainScreen extends MenuListScreen {
	private static final String[] BASE_OPTIONS = new String[] {
			"Sign out",
			"Create project",
			"Browse all projects",
			"Create activity",
			"Register work hours"
	};
	private static final String[] ADMIN_OPTIONS = new String[] {
			"Create employee"
	};

	private final String[] options;

	public MainScreen(ApplicationUI appUI) {
		super(appUI);

		List<String> options = new ArrayList<String>();

		for (String option : BASE_OPTIONS) {
			options.add(option);
		}

		if (appUI.getApp().getCurrentEmployee().isAdmin()) {
			for (String option : ADMIN_OPTIONS) {
				options.add(option);
			}
		}

		this.options = options.toArray(new String[0]);
	}

	@Override
	public String[] getOptions() {
		return options;
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
			Screen selectProjectDialog = new SelectProjectDialog(appUI, this, new Callback<Project>() {
				@Override
				public void callback(Screen source, PrintWriter out, Project project) {
					appUI.setScreen(new ViewProjectScreen(appUI, source, project));
				}
			});

			appUI.setScreen(selectProjectDialog);
		} else if (option.equals("Create employee")) {
			appUI.setScreen(new CreateEmployeeIdScreen(appUI));
		} else if (option.equals("Create activity")) {
			appUI.setScreen(new CreateActivityScreen(appUI, this));
		} else if (option.equals("Register work hours")) {
			Screen personalOrProjectsScreen = new PersonalOrProjectsScreen(appUI, this);
			appUI.setScreen(personalOrProjectsScreen);
		}
	}
}
