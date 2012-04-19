package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditProjectStartWeekScreen extends PromptScreen {
	private int startYear;
	private Project project;	

	public EditProjectStartWeekScreen(ApplicationUI appUI, int startYear, Project project) {
		super(appUI);
		
		this.startYear = startYear;
		this.project = project;
	}

	@Override
	public String getText() {
		Week startDate = project.getStartDate();
		
		if (startDate != null) {
			return String.format("Old start week: %d%nNew start week", startDate.getWeekNumber());
		}			
		return "New start week";
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
				project.setStartDate(startDate);
			} catch (IllegalArgumentException e) {
				out.println(e.getMessage() + ". Try again.");
				
				appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));
			}
			
			appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));
		}
		
		
	}
}
