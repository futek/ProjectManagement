package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.LocalDate;

import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectEndWeekScreen extends PromptScreen {
	private Screen source;
	private Project project;
	private int endWeekYear;

	public EditProjectEndWeekScreen(ApplicationUI appUI, Screen source, Project project, int endWeekYear) {
		super(appUI);

		this.source = source;
		this.project = project;
		this.endWeekYear = endWeekYear;
	}

	@Override
	public String getText() {
		LocalDate endDate = project.getEndDate();
		String text = "New end week number";

		if (endDate != null) {
			text = String.format("Old end week number: %d%n%s", endDate.getWeekOfWeekyear(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int endWeekNumber = -1;

		try {
			endWeekNumber = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (endWeekNumber < 0 || endWeekNumber > 53) {
			out.println("Invalid week number. Try again.");
		} else {
			try {
				project.setEndDate(endWeekYear, endWeekNumber);
			} catch (InvalidArgumentException e) {
				out.println(e.getMessage() + ". Try again.");
			}

			appUI.setScreen(source);
		}
	}
}
