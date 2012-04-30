package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;

public class RegisterTimeScreen extends PromptScreen {
	private Activity activity;

	public RegisterTimeScreen(ApplicationUI appUI, Screen source, Activity activity) {
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
			return;
		}

		try {
			appUI.getApp().getCurrentEmployee().registerTime(activity, duration);
		} catch (InvalidArgumentException e) {
			out.println(e.getMessage() + ". Try again.");
			return;
		}

		out.printf(
				"Time spent on activity \"%s\" today: %d min%n",
				activity.getName(),
				activity.getRegisteredTime(appUI.getApp().getCurrentEmployee(), appUI.getApp().getDate()));

		appUI.setScreen(new MainScreen(appUI));
	}
}