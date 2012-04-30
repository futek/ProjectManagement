package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.LocalDate;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;

public class EditActivityStartWeekScreen extends PromptScreen {
	private Screen source;
	private Activity activity;
	private int startWeekYear;

	public EditActivityStartWeekScreen(ApplicationUI appUI, Screen source, Activity activity, int startWeekYear) {
		super(appUI);

		this.source = source;
		this.activity = activity;
		this.startWeekYear = startWeekYear;
	}

	@Override
	public String getText() {
		LocalDate startDate = activity.getStartDate();
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

		if (startWeekNumber < 1 || startWeekNumber > 53) {
			out.println("Invalid week number. Try again.");
		} else {
			try {
				activity.setStartDate(startWeekYear, startWeekNumber);
			} catch (InvalidArgumentException e) {
				out.println(e.getMessage() + ". Try again.");
			}

			appUI.setScreen(source);
		}
	}
}
