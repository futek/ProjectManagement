package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public abstract class Screen {
	protected ApplicationUI appUI;
	
	public Screen(ApplicationUI appUI) {
		this.appUI = appUI;
	}
	
	public abstract void print(PrintWriter out);
	public abstract boolean processInput(String input, PrintWriter out);
}