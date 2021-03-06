package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Project;

public class PersonalOrProjectScreen extends MenuListScreen {
	private Screen source;

	public PersonalOrProjectScreen(ApplicationUI appUI, Screen source) {
		super(appUI);

		this.source = source;
	}

	@Override
	public String[] getOptions() {
		List<String> options = new ArrayList<String>();

		options.add("Back");
		options.add("Personal activities");
		options.add("Project activities");

		return options.toArray(new String[0]);
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Back")) {
			appUI.setScreen(source);
		} else if (option.equals("Personal activities")) {
			SelectActivityDialog selectActivityDialog = new SelectActivityDialog(appUI, source, appUI.getApp().getCurrentEmployee(), new Callback<Activity>() {
				@Override
				public void callback(Screen source, PrintWriter out, Activity activity) {
					appUI.setScreen(new RegisterTimeScreen(appUI, source, activity));
				}
			});

			appUI.setScreen(selectActivityDialog);
		} else if (option.equals("Project activities")) {
			SelectProjectDialog selectProjectDialog = new SelectProjectDialog(appUI, source, appUI.getApp().getCurrentEmployee(), new Callback<Project>() {
				@Override
				public void callback(Screen source, PrintWriter out, Project project) {
					SelectActivityDialog selectActivityDialog = new SelectActivityDialog(appUI, source, project, new Callback<Activity>() {
						@Override
						public void callback(Screen source, PrintWriter out, Activity activity) {
							appUI.setScreen(new RegisterTimeScreen(appUI, source, activity));
						}
					});

					appUI.setScreen(selectActivityDialog);
				}
			});

			appUI.setScreen(selectProjectDialog);
		}
	}
}
