package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public class EmployeeScreen extends Screen {
	public EmployeeScreen(ApplicationUI appUI) {
		super(appUI);
	}

	@Override
	public void printMenu(PrintWriter out) {
		out.print("0) Sign Out\n" +
							"1) Create Project\n" +
				  		"> ");
		out.flush();
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		if (input.equals("0")) {
			appUI.getApplication().SignOut();
			out.println("You signed out.");
			appUI.setScreen(new StartScreen(appUI));
		} else if (input.equals("1")) {
			out.println("");
			appUI.setScreen(new CreateProjectNameScreen(appUI));
		} else {
			out.println("Wrong selection.");
		}

		return false;
	}
}