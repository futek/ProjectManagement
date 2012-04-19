package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Project;
import dk.softwarehuset.projectmanagement.app.Week;

public class EditProjectEndYearScreen extends PromptScreen {
	private Project project;
	

	public EditProjectEndYearScreen(ApplicationUI appUI, Project project) {
		super(appUI);
		
		this.project = project;
	}

	@Override
	public String getText() {
		Week endDate = project.getEndDate();
		
		if (endDate != null) {
			return String.format("Old end year: %d%nNew end year", endDate.getYear());
		}
		
		return "New end year";
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
			appUI.setScreen(new EditProjectEndWeekScreen(appUI, endYear, project));
		}
		
	}
}
