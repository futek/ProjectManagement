package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.LocalDate;

import dk.softwarehuset.projectmanagement.app.Project;

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
		LocalDate startDate = project.getStartDate();
		String text = "New start week year";

		if (startDate != null) {
			text = String.format("Old start week year: %d%n%s", startDate.getWeekyear(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int startWeekYear = -1;

		try {
			startWeekYear = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (startWeekYear < 0) {
			out.println("Invalid week year. Try again.");
		} else {
			appUI.setScreen(new EditProjectStartWeekScreen(appUI, source, project, startWeekYear));
		}
	}
}
