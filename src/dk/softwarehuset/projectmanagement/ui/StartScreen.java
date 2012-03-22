package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public class StartScreen extends Screen {
	public StartScreen(ApplicationUI appUI) {
		super(appUI);
	}

	@Override
	public void printMenu(PrintWriter out) {
		out.print("0) Exit\n" +
				  "1) Sign In\n" +
				  "> ");
		out.flush();
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		if (input.equals("0")) {
			out.println("Exited.");
			return true;
		} else if (input.equals("1")) {
			out.println();
			appUI.setScreen(new LoginScreen(appUI));
		} else {
			out.println("Wrong selection.");
		}
		
		return false;
	}
}