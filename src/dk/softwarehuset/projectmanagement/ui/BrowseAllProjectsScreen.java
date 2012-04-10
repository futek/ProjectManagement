package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.Collection;

import dk.softwarehuset.projectmanagement.app.Project;

// TODO: Fix this..

public class BrowseAllProjectsScreen extends Screen {
	private static String generateMenu(ApplicationUI appUI) {
		Collection<Project> projects = appUI.getApp().getProjects().values();
		String menu = "0) Exit\n";

		int i = 1;
		for (Project project : projects) {
			menu += String.format("%d) %s\n", i, project.getName());
			i++;
		}

		menu += "> ";

		return menu;
	}

	public BrowseAllProjectsScreen(ApplicationUI appUI) {
		super(appUI);
	}

	@Override
	public void print(PrintWriter out) {
		out.print(generateMenu(appUI));
		out.flush();
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		if (input.equals("0")) {
			appUI.setScreen(new MainScreen(appUI));
		}
	}
}