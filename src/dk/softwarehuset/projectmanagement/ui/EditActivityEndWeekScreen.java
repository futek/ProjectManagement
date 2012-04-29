package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.LocalDate;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;

public class EditActivityEndWeekScreen extends PromptScreen {
	private Screen source;
	private Activity activity;
	private int endWeekYear;

	public EditActivityEndWeekScreen(ApplicationUI appUI, Screen source, Activity activity, int endWeekYear) {
		super(appUI);

		this.source = source;
		this.activity = activity;
		this.endWeekYear = endWeekYear;
	}

	@Override
	public String getText() {
		LocalDate endDate = activity.getEndDate();
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
				activity.setEndDate(endWeekYear, endWeekNumber);
			} catch (InvalidArgumentException e) {
				out.println(e.getMessage() + ". Try again.");
			}

			appUI.setScreen(source);
		}

	}
}
