package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;

public class EditActivityNameScreen extends PromptScreen {
	private Screen source;
	private Activity activity;

	public EditActivityNameScreen(ApplicationUI appUI, Screen source, Activity activity) {
		super(appUI);

		this.source = source;
		this.activity = activity;
	}

	@Override
	public String getText() {
		return String.format("Old activity name: %s%nNew activity name", activity.getName());
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		String name = input.trim();

		out.printf("Activity \"%s\" changed to \"%s\".%n", activity.getName(), name);

		activity.setName(name);

		appUI.setScreen(source);
	}
}