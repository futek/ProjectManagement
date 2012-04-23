package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditProjectStartYearScreen extends PromptScreen {
	private Project project;

	public EditProjectStartYearScreen(ApplicationUI appUI, Project project) {
		super(appUI);

		this.project = project;
	}

	@Override
	public String getText() {
		Week startDate = project.getStartDate();

		if (startDate != null) {
			return String.format("Old start year: %d%nNew start year", startDate.getYear());
		}

		return "New start year";
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
			appUI.setScreen(new EditProjectStartWeekScreen(appUI, startYear, project));
		}

	}
}
