package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;

import dk.softwarehuset.projectmanagement.app.Activity;

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
		LocalDate startDate = activity.getStartDate();
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
			appUI.setScreen(new EditActivityStartWeekScreen(appUI, source, activity, startWeekYear));
		} catch (NumberFormatException e) {
			out.println("Invalid week year. Try again");
		} catch (IllegalFieldValueException e) {
			out.println("Invalid week year. Try again");
		}
	}
}