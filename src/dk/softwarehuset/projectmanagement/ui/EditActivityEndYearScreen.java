package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.IllegalFieldValueException;
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
		try {
			int endWeekYear = Integer.parseInt(input);
			(new LocalDate()).withWeekyear(endWeekYear); // valid week year?
			appUI.setScreen(new EditActivityEndWeekScreen(appUI, source, activity, endWeekYear));
		} catch (NumberFormatException e) {
			out.println("Invalid week year. Try again");
		} catch (IllegalFieldValueException e) {
			out.println("Invalid week year. Try again");
		}
	}
}