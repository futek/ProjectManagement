package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditProjectEndWeekScreen extends PromptScreen {
	private int endYear;
	private Project project;	

	public EditProjectEndWeekScreen(ApplicationUI appUI, int endYear, Project project) {
		super(appUI);
		
		this.endYear = endYear;
		this.project = project;
	}

	@Override
	public String getText() {
		Week endDate = project.getEndDate();
		
		if (endDate != null) {
			return String.format("Old end week: %d%nNew end week", endDate.getWeekNumber());
		}			
		return "New end week";
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
				project.setEndDate(endDate);
			} catch (IllegalArgumentException e) {
				out.println(e.getMessage() + ". Try again.");
				
				appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));
			}
			
			appUI.setScreen(new EditProjectPropertiesScreen(appUI, project));
		}
		
		
	}
}
