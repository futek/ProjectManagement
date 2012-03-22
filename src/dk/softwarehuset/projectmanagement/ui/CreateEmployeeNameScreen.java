package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.NonUniqueIdentifierException;
import dk.softwarehuset.projectmanagement.app.PermissionDeniedException;

public class CreateEmployeeNameScreen extends Screen {
	private String id;
	
	public CreateEmployeeNameScreen(ApplicationUI appUI, String id) {
		super(appUI);
		
		this.id = id;
	}

	@Override
	public void printMenu(PrintWriter out) {
		out.print("New employee name: ");
		out.flush();
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		String name = input.trim();
		
		Employee employee = new Employee(id, name);
		
		try {
			appUI.getApplication().addEmployee(employee);
		} catch (NonUniqueIdentifierException e) {
			out.println("Employee id taken.");
			appUI.setScreen(new AdminScreen(appUI));
			return false;
		} catch (PermissionDeniedException e) {
			out.println("Not admin.");
			appUI.setScreen(new StartScreen(appUI));
			return false;
		}
		
		out.println();
		appUI.setScreen(new AdminScreen(appUI));
		
		return false;
	}
}