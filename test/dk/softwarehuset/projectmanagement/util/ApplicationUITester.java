package dk.softwarehuset.projectmanagement.util;

import static org.junit.Assert.assertEquals;
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

	public ApplicationUITester(ApplicationUI appUI) {
		this.appUI = appUI;
	}

	public void assertOptionExists(String option) {
		StringWriter out = new StringWriter();
		appUI.printMenu(new PrintWriter(out));

		Pattern pattern = Pattern.compile("^\\d+\\) " + Pattern.quote(option) + "$", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(out.toString());

		assertTrue("Menu option \"" + option + "\" not found", matcher.find());
	}

	// TODO: Use template method

	public void selectOption(String option) throws IOException {
		selectOption(option, false);
	}

	public void selectOption(String option, boolean expectedExitStatus) throws IOException {
		StringWriter out = new StringWriter();
		appUI.printMenu(new PrintWriter(out));

		Pattern pattern = Pattern.compile("^(\\d+)\\) " + Pattern.quote(option) + "$", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(out.toString());

		assertTrue("Menu option \"" + option + "\" not found", matcher.find());

		String input = matcher.group(1);
		BufferedReader reader = new BufferedReader(new StringReader(input));
		String line = appUI.readInput(reader);

		assertEquals(input, line);

		out = new StringWriter();
		boolean exitStatus = appUI.processInput(line, new PrintWriter(out));

		assertEquals("", out.toString());
		assertEquals(exitStatus, expectedExitStatus);
	}

	public void selectOption(String option, String expectedOutput) throws IOException {
		selectOption(option, expectedOutput, false);
	}

	public void selectOption(String option, String expectedOutput, boolean expectedExitStatus) throws IOException {
		StringWriter out = new StringWriter();
		appUI.printMenu(new PrintWriter(out));

		Pattern pattern = Pattern.compile("^(\\d+)\\) " + Pattern.quote(option) + "$", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(out.toString());

		assertTrue("Menu option \"" + option + "\" not found", matcher.find());

		String input = matcher.group(1);
		BufferedReader reader = new BufferedReader(new StringReader(input));
		String line = appUI.readInput(reader);

		assertEquals(input, line);

		out = new StringWriter();
		boolean exitStatus = appUI.processInput(line, new PrintWriter(out));
		String eol = System.getProperty("line.separator");

		assertEquals(expectedOutput + eol, out.toString());
		assertEquals(exitStatus, expectedExitStatus);
	}

	public void promptInput(String expectedMenu, String input) throws IOException {
		promptInput(expectedMenu, input, false);
	}

	public void promptInput(String expectedMenu, String input, boolean expectedExitStatus) throws IOException {
		StringWriter out = new StringWriter();
		appUI.printMenu(new PrintWriter(out));

		assertEquals(expectedMenu, out.toString());

		BufferedReader reader = new BufferedReader(new StringReader(input));
		String line = appUI.readInput(reader);

		assertEquals(input, line);

		out = new StringWriter();
		boolean exitStatus = appUI.processInput(line, new PrintWriter(out));

		assertEquals("", out.toString());
		assertEquals(expectedExitStatus, exitStatus);
	}

	public void promptInput(String expectedMenu, String input, String expectedOutput) throws IOException {
		promptInput(expectedMenu, input, expectedOutput, false);
	}

	public void promptInput(String expectedMenu, String input, String expectedOutput, boolean expectedExitStatus) throws IOException {
		StringWriter out = new StringWriter();
		appUI.printMenu(new PrintWriter(out));

		assertEquals(expectedMenu, out.toString());

		BufferedReader reader = new BufferedReader(new StringReader(input));
		String line = appUI.readInput(reader);

		assertEquals(input, line);

		out = new StringWriter();
		boolean exitStatus = appUI.processInput(line, new PrintWriter(out));
		String eol = System.getProperty("line.separator");

		assertEquals(expectedOutput + eol, out.toString());
		assertEquals(expectedExitStatus, exitStatus);
	}

	public void input(String input) throws IOException {
		input(input, false);
	}

	public void input(String input, boolean expectedExitStatus) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(input));
		String line = appUI.readInput(reader);

		assertEquals(input, line);

		StringWriter out = new StringWriter();
		boolean exitStatus = appUI.processInput(line, new PrintWriter(out));

		assertEquals("", out.toString());
		assertEquals(expectedExitStatus, exitStatus);
	}

	public void input(String input, String expectedOutput) throws IOException {
		input(input, expectedOutput, false);
	}

	public void input(String input, String expectedOutput, boolean expectedExitStatus) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(input));
		String line = appUI.readInput(reader);

		assertEquals(input, line);

		StringWriter out = new StringWriter();
		boolean exitStatus = appUI.processInput(line, new PrintWriter(out));
		String eol = System.getProperty("line.separator");

		assertEquals(expectedOutput + eol, out.toString());
		assertEquals(expectedExitStatus, exitStatus);
	}
}
