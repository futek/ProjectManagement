package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.List;

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

	@Override
	public String[] getOptions() {
		String[] options = new String[projects.size() + 1];

		options[0] = "Back";

		int i = 1;
		for (Project project : projects) {
			options[i++] = String.format("[%s] %s", project.getId(), project.getName());
		}

		return options;
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