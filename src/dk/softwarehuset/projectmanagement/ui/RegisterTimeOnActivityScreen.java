package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;

public class RegisterTimeOnActivityScreen extends PromptScreen {
	private Activity activity;

	public RegisterTimeOnActivityScreen(ApplicationUI appUI, Screen source, Activity activity) {
		super(appUI);

		this.activity = activity;
	}

	@Override
	public String getText() {
		return String.format(
				"Time spent on activity \"%s\" today: %d min%nRegister time (in minutes)",
				activity.getName(),
				activity.getRegisteredTime(appUI.getApp().getCurrentEmployee(), appUI.getApp().getDate()));
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int duration = -1;

		try {
			duration = Integer.parseInt(input);
		} catch (NumberFormatException e) {
		}

		if (duration < 0) {
			out.println("Invalid duration. Try again.");
		} else {
			appUI.getApp().getCurrentEmployee().registerTime(activity, duration);

			out.printf(
					"Time spent on activity \"%s\" today: %d min%n",
					activity.getName(),
					activity.getRegisteredTime(appUI.getApp().getCurrentEmployee(), appUI.getApp().getDate()));

			appUI.setScreen(new MainScreen(appUI));
		}
	}
}