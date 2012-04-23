package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.Project;

public class SelectActivityDialog extends MenuListScreen {
	private Screen source;
	private Callback<Activity> callback;
	private List<Activity> activities;

	public SelectActivityDialog(ApplicationUI appUI, Screen source, Project project, Callback<Activity> callback) {
		super(appUI);

		this.source = source;
		this.callback = callback;

		activities = project.getActivities();
	}

	public SelectActivityDialog(ApplicationUI appUI, Screen source, Employee employee, Callback<Activity> callback) {
		super(appUI);

		this.source = source;
		this.callback = callback;

		activities = employee.getActivities();
	}

	@Override
	public String[] getOptions() {
		String[] options = new String[activities.size() + 1];

		options[0] = "Back";

		int i = 1;
		for (Activity activity : activities) {
			options[i++] = String.format("%s", activity.getName());
		}

		return options;
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (index == 0) {
			appUI.setScreen(source);
		} else {
			Activity activity = activities.get(index - 1);

			callback.callback(this, out, activity);
		}
	}
}