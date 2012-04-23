package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditActivityEndWeekScreen extends PromptScreen {
	private Screen source;
	private Activity activity;
	private int endYear;

	public EditActivityEndWeekScreen(ApplicationUI appUI, Screen source, Activity activity, int startYear) {
		super(appUI);

		this.source = source;
		this.activity = activity;
		this.endYear = startYear;
	}

	@Override
	public String getText() {
		Week endDate = activity.getEndDate();
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
				activity.setEndDate(endDate);
			} catch (IllegalArgumentException e) {
				out.println(e.getMessage() + ". Try again.");

				appUI.setScreen(source);
			}

			appUI.setScreen(source);
		}

	}
}
