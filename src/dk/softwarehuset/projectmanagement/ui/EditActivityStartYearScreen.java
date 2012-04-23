package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditActivityStartYearScreen extends PromptScreen {
	private Screen source;
	private Activity activity;

	public EditActivityStartYearScreen(ApplicationUI appUI, Screen source, Activity activity) {
		super(appUI);

		this.source = source;
		this.activity = activity;
	}

	@Override
	public String getText() {
		Week startDate = activity.getStartDate();
		String text = "New start year";

		if (startDate != null) {
			text = String.format("Old start year: %d%n%s", startDate.getYear(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int startYear = -1;

		try {
			startYear = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (startYear < 0) {
			out.println("Invalid year. Try again.");
		} else {
			appUI.setScreen(new EditActivityStartWeekScreen(appUI, source, activity, startYear));
		}
	}
}
