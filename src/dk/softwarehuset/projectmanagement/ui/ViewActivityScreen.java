package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;

public class ViewActivityScreen extends MenuListScreen {
	private static final String[] OPTIONS = new String[] {
			"Back"
	};

	private Screen source;
	private Activity activity;

	public ViewActivityScreen(ApplicationUI appUI, Screen source, Activity activity) {
		super(appUI);

		this.source = source;
		this.activity = activity;
	}

	@Override
	public String[] getOptions() {
		return OPTIONS;
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Back")) {
			appUI.setScreen(source);
		}
	}
}
