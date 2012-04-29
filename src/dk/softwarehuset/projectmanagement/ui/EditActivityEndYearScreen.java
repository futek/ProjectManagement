package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.LocalDate;

import dk.softwarehuset.projectmanagement.app.Activity;

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
		LocalDate endDate = activity.getEndDate();
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
			out.println("Invalid year. Try again.");
		} else {
			appUI.setScreen(new EditActivityEndWeekScreen(appUI, source, activity, endWeekYear));
		}
	}
}
