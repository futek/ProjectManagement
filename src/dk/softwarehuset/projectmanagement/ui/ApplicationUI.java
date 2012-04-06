package dk.softwarehuset.projectmanagement.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Application;

public class ApplicationUI {
	private Application app;
	private boolean running;
	private Screen currentScreen;

	public ApplicationUI(Application app) {
		this.app = app;

		setScreen(new StartScreen(this));

		running = true;
	}

	public void setScreen(Screen screen) {
		currentScreen = screen;
	}

	public void render(PrintWriter out) {
		currentScreen.print(out);
	}

	public String readInput(BufferedReader in) throws IOException {
		return in.readLine();
	}

	public void processInput(String input, PrintWriter out) {
		currentScreen.processInput(input, out);
	}

	public void run() throws IOException {
		running = true;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);

		while (running) {
			render(out);
			processInput(readInput(in), out);
		}
	}

	public Application getApp() {
		return app;
	}

	public void exit() {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	public static void main(String[] args) throws IOException {
		Application app = new Application();
		ApplicationUI appUI = new ApplicationUI(app);

		appUI.run();
	}
}
