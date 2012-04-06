package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public abstract class MenuListScreen extends Screen {
	private String[] options;

	public MenuListScreen(ApplicationUI appUI, String[] options) {
		super(appUI);

		this.options = options;
	}

	@Override
	public void print(PrintWriter out) {
		for (int i = 0; i < options.length; i++) {
			out.printf("%d) %s\n", i, options[i]);
		}

		out.print("> ");
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
			optionSelected(options[index], out);
		} else {
			invalidOptionSelected(out);
		}
	}

	public void optionSelected(String option, PrintWriter out) {
	}

	public void invalidOptionSelected(PrintWriter out) {
		out.println("Invalid option selected.");
	}
}