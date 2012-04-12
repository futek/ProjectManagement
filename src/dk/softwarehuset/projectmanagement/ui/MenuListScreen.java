package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public abstract class MenuListScreen extends Screen {
	private String[] options;

	public MenuListScreen(ApplicationUI appUI) {
		super(appUI);
	}

	public abstract String[] getOptions();

	@Override
	public void print(PrintWriter out) {
		options = getOptions();

		int i = 0;
		for (String option : options) {
			out.printf("%d) %s%n", i++, option);
		}

		out.print("-> ");
		out.flush();
	}

	@Override
	public void processInput(String input, PrintWriter out) {
		int index = -1;

		try {
			index = Integer.parseInt(input.trim());
		} catch (NumberFormatException e) {
		}

		if (index >= 0 && index < options.length) {
			optionSelected(index, options[index], out);
		} else {
			invalidOptionSelected(out);
		}
	}

	public void optionSelected(int index, String option, PrintWriter out) {
	}

	public void invalidOptionSelected(PrintWriter out) {
		out.println("Invalid option selected.");
	}
}