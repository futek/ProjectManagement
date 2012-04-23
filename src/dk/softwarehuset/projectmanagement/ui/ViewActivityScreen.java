package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Activity;

public class ViewActivityScreen extends MenuListScreen {
	private Screen source;
	private Activity activity;

	public ViewActivityScreen(ApplicationUI appUI, Screen source, Activity activity) {
		super(appUI);

		this.source = source;
		this.activity = activity;
	}

	@Override
	public String[] getOptions() {
		List<String> options = new ArrayList<String>();

		options.add("Back");
		options.add("Edit properties"); // TODO: Limit to project leader?

		return options.toArray(new String[0]);
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Back")) {
			appUI.setScreen(source);
		} else if (option.equals("Edit properties")) {
			appUI.setScreen(new EditActivityPropertiesScreen(appUI, source, activity));
		}
	}
}
