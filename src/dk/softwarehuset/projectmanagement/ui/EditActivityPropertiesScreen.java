package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Activity;

public class EditActivityPropertiesScreen extends MenuListScreen {
	private static final String[] OPTIONS = {
			"Back",
			"Edit name",
			"Edit start date",
			"Edit end date"
	};

	private Activity activity;
	private Screen source;

	public EditActivityPropertiesScreen(ApplicationUI appUI, Screen source, Activity activity) {
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
		} else if (option.equals("Edit name")) {
			appUI.setScreen(new EditActivityNameScreen(appUI, this, activity));
		} else if (option.equals("Edit start date")) {
			appUI.setScreen(new EditActivityStartYearScreen(appUI, this, activity));
		} else if (option.equals("Edit end date")) {
			appUI.setScreen(new EditActivityEndYearScreen(appUI, this, activity));
		}
	}
}