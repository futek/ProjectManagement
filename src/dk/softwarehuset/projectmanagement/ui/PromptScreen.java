package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public abstract class PromptScreen extends Screen {
	public PromptScreen(ApplicationUI appUI) {
		super(appUI);
	}

	public abstract String getText();

	@Override
	public void print(PrintWriter out) {
		String text = getText();

		out.printf("%s: ", text);
		out.flush();
	}
}