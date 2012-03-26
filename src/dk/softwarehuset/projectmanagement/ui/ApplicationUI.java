package dk.softwarehuset.projectmanagement.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Application;

public class ApplicationUI {
	private Application app;
	private Screen currentScreen;
	
	public ApplicationUI(Application app) {
		this.app = app;
		setScreen(new StartScreen(this));
	}

	public Application getApplication() {
		return app;
	}
	
	public void setScreen(Screen screen) {
		currentScreen = screen;
	}
	
	public void printMenu(PrintWriter out) {
		currentScreen.print(out);
	}

	public String readInput(BufferedReader in) throws IOException {
		return in.readLine();
	}

	public boolean processInput(String input, PrintWriter out) {
		return currentScreen.processInput(input, out);
	}
	
	public static void main(String[] args) throws IOException {
		Application app = new Application();
		ApplicationUI appUI = new ApplicationUI(app);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);
		
		String selection;
		
		do {
			appUI.printMenu(out);
			selection = appUI.readInput(in);
		} while (!appUI.processInput(selection, out));
	}
}
