package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.IllegalFieldValueException;
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
		try {
			int startWeekYear = Integer.parseInt(input);
			(new LocalDate()).withWeekyear(startWeekYear); // valid week year?
			appUI.setScreen(new EditProjectStartWeekScreen(appUI, source, project, startWeekYear));
		} catch (NumberFormatException e) {
			out.println("Invalid week year. Try again.");
		} catch (IllegalFieldValueException e) {
			out.println("Invalid week year. Try again.");
		}
	}
}