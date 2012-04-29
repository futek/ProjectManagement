package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.LocalDate;

import dk.softwarehuset.projectmanagement.app.Project;

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
		LocalDate endDate = project.getEndDate();
		String text = "New end week year";

		if (endDate != null) {
			text = String.format("Old end week year: %d%n%s", endDate.getWeekyear(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int endWeekYear = -1;

		try {
			endWeekYear = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (endWeekYear < 0) {
			out.println("Invalid week year. Try again.");
		} else {
			appUI.setScreen(new EditProjectEndWeekScreen(appUI, source, project, endWeekYear));
		}
	}
}
