package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditActivityEndYearScreen extends PromptScreen {
	private Screen source;
	private Activity activity;

	public EditActivityEndYearScreen(ApplicationUI appUI, Screen source, Activity activity) {
		super(appUI);

		this.source = source;
		this.activity = activity;
	}

	@Override
	public String getText() {
		Week endDate = activity.getEndDate();
		String text = "New end year";

		if (endDate != null) {
			text = String.format("Old end year: %d%n%s", endDate.getYear(), text);
		}

		return text;
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int endYear = -1;

		try {
			endYear = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (endYear < 0) {
			out.println("Invalid year. Try again.");
		} else {
			appUI.setScreen(new EditActivityEndWeekScreen(appUI, source, activity, endYear));
		}
	}
}
