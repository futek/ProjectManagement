package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditProjectEndWeekScreen extends PromptScreen {
	private Screen source;
	private Project project;
	private int endYear;

	public EditProjectEndWeekScreen(ApplicationUI appUI, Screen source, Project project, int endYear) {
		super(appUI);

		this.source = source;
		this.project = project;
		this.endYear = endYear;
	}

	@Override
	public String getText() {
		Week endDate = project.getEndDate();
		String text = "New end week";

		if (endDate != null) {
			text = String.format("Old end week: %d%n%s", endDate.getWeekNumber(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int endWeek = -1;

		try {
			endWeek = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (endWeek < 0 || endWeek > 53) {
			out.println("Invalid week number. Try again.");
		} else {
			Week endDate = new Week(endYear, endWeek);

			try {
				project.setEndDate(endDate);
			} catch (IllegalArgumentException e) {
				out.println(e.getMessage() + ". Try again.");

				appUI.setScreen(source);
			}

			appUI.setScreen(source);
		}
	}
}
