package dk.softwarehuset.projectmanagement.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dk.softwarehuset.projectmanagement.ui.ApplicationUI;

public class ApplicationUITester {
	private ApplicationUI appUI;
	private int state = 0;
	private String lastOutput;
	private boolean lastExitStatus;

	public ApplicationUITester(ApplicationUI appUI) {
		this.appUI = appUI;
	}

	private String render() {
		StringWriter out = new StringWriter();
		appUI.render(new PrintWriter(out));

		return out.toString();
	}

	public ApplicationUITester expect(String... expectedLines) {
		if (state == 0) {
			assertArrayEquals("Unexpected menu", expectedLines, render().split("\n"));
		} else if (state == 1) {
			assertArrayEquals("Unexpected response", expectedLines, lastOutput.split("\n"));

			state = 0;
		}

		return this;
	}

	public ApplicationUITester expectOption(String option) {
		Pattern pattern = Pattern.compile("^(\\d+)\\) " + Pattern.quote(option) + "$", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(render());

		assertTrue("Menu option \"" + option + "\" not found", matcher.find());

		return this;
	}

	public ApplicationUITester expectNoOption(String option) {
		Pattern pattern = Pattern.compile("^(\\d+)\\) " + Pattern.quote(option) + "$", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(render());

		assertFalse("Menu option \"" + option + "\" found", matcher.find());

		return this;
	}

	public void expectNothing() {
		if (state == 0) {
			assertEquals("Menu not empty", "", render());
		} else if (state == 1) {
			assertEquals("Response not empty", "", lastOutput);

			state = 0;
		}
	}

	public void expectExit() {
		assertTrue("Application still running", lastExitStatus);
	}

	public ApplicationUITester write(String text) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(String.format("%s%n", text)));
		String input = appUI.readInput(reader);

		assertEquals("Unexpected input", text, input);

		StringWriter out = new StringWriter();
		appUI.processInput(input, new PrintWriter(out));

		lastOutput = out.toString();
		lastExitStatus = !appUI.isRunning();

		state = 1;

		return this;
	}

	public ApplicationUITester selectOption(String option) throws IOException {
		Pattern pattern = Pattern.compile("^(\\d+)\\) " + Pattern.quote(option) + "$", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(render());

		assertTrue("Menu option \"" + option + "\" not found", matcher.find());

		String input = matcher.group(1);

		StringWriter out = new StringWriter();
		appUI.processInput(input, new PrintWriter(out));

		lastOutput = out.toString();
		lastExitStatus = !appUI.isRunning();

		state = 1;

		return this;
	}
}