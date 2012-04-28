package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.Project;

public class SelectProjectDialog extends MenuListScreen {
	private Screen source;
	private List<Project> projects = appUI.getApp().getProjects();
	private Callback<Project> callback;

	public SelectProjectDialog(ApplicationUI appUI, Screen source, Callback<Project> callback) {
		super(appUI);

		this.source = source;
		this.callback = callback;
	}

	// Present only projects employee joined
	public SelectProjectDialog(ApplicationUI appUI, Screen source, Employee employee, Callback<Project> callback) {
		super(appUI);

		this.source = source;
		this.callback = callback;

		List<Project> filteredProjects = new ArrayList<Project>();
		for (Project project : projects) {
			if (project.getEmployees().contains(employee)) {
				filteredProjects.add(project);
			}
		}
		projects = filteredProjects;
	}

	@Override
	public String[] getOptions() {
		List<String> options = new ArrayList<String>();

		options.add("Back");

		for (Project project : projects) {
			options.add(String.format("[%s] %s", project.getId(), project.getName()));
		}

		return options.toArray(new String[0]);
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (index == 0) {
			appUI.setScreen(source);
		} else {
			Project project = projects.get(index - 1);

			callback.callback(this, out, project);
		}
	}
}