package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditProjectEndYearScreen extends PromptScreen {
	private Screen source;
	private Project project;

	public EditProjectEndYearScreen(ApplicationUI appUI, Screen source, Project project) {
		super(appUI);

		this.source = source;
		this.project = project;
	}

	@Override
	public String getText() {
		Week endDate = project.getEndDate();
		String text = "New end year";

		if (endDate != null) {
			text = String.format("Old end year: %d%n%s", endDate.getYear(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int endYear = -1;

		try {
			endYear = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (endYear < 0) {
			out.println("Invalid year. Try again.");
		} else {
			appUI.setScreen(new EditProjectEndWeekScreen(appUI, source, project, endYear));
		}
	}
}
