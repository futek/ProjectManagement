package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public abstract class PromptScreen extends Screen {
	private String prompt;

	public PromptScreen(ApplicationUI appUI, String prompt) {
		super(appUI);

		this.prompt = prompt;
	}

	@Override
	public void print(PrintWriter out) {
		out.printf("%s: ", prompt);
		out.flush();
	}
}