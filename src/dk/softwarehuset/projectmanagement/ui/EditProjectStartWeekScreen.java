package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.LocalDate;

import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.app.Project;

public class EditProjectStartWeekScreen extends PromptScreen {
	private Screen source;
	private Project project;
	private int startWeekYear;

	public EditProjectStartWeekScreen(ApplicationUI appUI, Screen source, Project project, int startWeekYear) {
		super(appUI);

		this.source = source;
		this.project = project;
		this.startWeekYear = startWeekYear;
	}

	@Override
	public String getText() {
		LocalDate startDate = project.getStartDate();
		String text = "New start week number";

		if (startDate != null) {
			text = String.format("Old start week number: %d%n%s", startDate.getWeekOfWeekyear(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int startWeekNumber = -1;

		try {
			startWeekNumber = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (startWeekNumber < 0 || startWeekNumber > 53) {
			out.println("Invalid week number. Try again.");
		} else {
			try {
				project.setStartDate(startWeekYear, startWeekNumber);
			} catch (InvalidArgumentException e) {
				out.println(e.getMessage() + ". Try again.");
			}

			appUI.setScreen(source);
		}
	}
}
