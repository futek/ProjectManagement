package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditProjectStartYearScreen extends PromptScreen {
	private Screen source;
	private Project project;

	public EditProjectStartYearScreen(ApplicationUI appUI, Screen source, Project project) {
		super(appUI);

		this.source = source;
		this.project = project;
	}

	@Override
	public String getText() {
		Week startDate = project.getStartDate();
		String text = "New start year";

		if (startDate != null) {
			text = String.format("Old start year: %d%n%s", startDate.getYear(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int startYear = -1;

		try {
			startYear = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (startYear < 0) {
			out.println("Invalid year. Try again.");
		} else {
			appUI.setScreen(new EditProjectStartWeekScreen(appUI, source, project, startYear));
		}
	}
}
