package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public class StartScreen extends MenuListScreen {
	private static String[] OPTIONS = new String[] {
			"Exit",
			"Sign in"
	};

	public StartScreen(ApplicationUI appUI) {
		super(appUI, OPTIONS);
	}

	@Override
	public void optionSelected(String option, PrintWriter out) {
		if (option.equals("Exit")) {
			out.println("Exited.");
			appUI.exit();
			return;
		} else if (option.equals("Sign in")) {
			appUI.setScreen(new SignInScreen(appUI));
		}
	}
}