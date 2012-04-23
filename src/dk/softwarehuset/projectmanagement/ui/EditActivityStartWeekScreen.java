package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditActivityStartWeekScreen extends PromptScreen {
	private Screen source;
	private Activity activity;
	private int startYear;

	public EditActivityStartWeekScreen(ApplicationUI appUI, Screen source, Activity activity, int startYear) {
		super(appUI);

		this.source = source;
		this.activity = activity;
		this.startYear = startYear;
	}

	@Override
	public String getText() {
		Week startDate = activity.getStartDate();
		String text = "New start week";

		if (startDate != null) {
			text = String.format("Old start week: %d%n%s", startDate.getWeekNumber(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int startWeek = -1;

		try {
			startWeek = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (startWeek < 0 || startWeek > 53) {
			out.println("Invalid week number. Try again.");
		} else {
			Week startDate = new Week(startYear, startWeek);

			try {
				activity.setStartDate(startDate);
			} catch (IllegalArgumentException e) {
				out.println(e.getMessage() + ". Try again.");

				appUI.setScreen(source);
			}

			appUI.setScreen(source);
		}

	}
}
