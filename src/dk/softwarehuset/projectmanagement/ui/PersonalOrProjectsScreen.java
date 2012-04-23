package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Project;

public class PersonalOrProjectsScreen extends MenuListScreen {
	private Screen source;

	public PersonalOrProjectsScreen(ApplicationUI appUI, Screen source) {
		super(appUI);

		this.source = source;
	}

	@Override
	public String[] getOptions() {
		List<String> options = new ArrayList<String>();

		options.add("Back");
		options.add("Personal activities");
		options.add("Projects");

		return options.toArray(new String[0]);
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Back")) {
			appUI.setScreen(source);
		} else if (option.equals("Personal activities")){
			appUI.setScreen(new MyActivitiesScreen(appUI));
		} else if (option.equals("Projects")) {
			SelectProjectDialog selectProjectDialog = new SelectProjectDialog(appUI, source, new Callback<Project>() {
				@Override
				public void callback(Screen source, PrintWriter out, Project project) {
					SelectActivityDialog selectActivityDialog = new SelectActivityDialog(appUI, source, project, new Callback<Activity>() {
						@Override
						public void callback(Screen source, PrintWriter out, Activity activity) {
							appUI.setScreen(new RegisterTimeOnActivityScreen(appUI, source, activity));
						}
					});
					
					appUI.setScreen(selectActivityDialog);
				}				
			});
			
			appUI.setScreen(selectProjectDialog);			
		}
	}
}
