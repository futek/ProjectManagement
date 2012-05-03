package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.Project;

public class SelectEmployeeDialog extends MenuListScreen {
	private Screen source;
	private Callback<Employee> callback;
	private List<Employee> employees = appUI.getApp().getEmployees();

	public SelectEmployeeDialog(ApplicationUI appUI, Screen source, Callback<Employee> callback) {
		super(appUI);

		this.source = source;
		this.callback = callback;
	}

	// Present only employee who joined a specific project
	public SelectEmployeeDialog(ApplicationUI appUI, Screen source, Project project, Callback<Employee> callback) {
		super(appUI);

		this.source = source;
		this.callback = callback;

		List<Employee> filteredEmployees = new ArrayList<Employee>();
		for (Employee employee : employees) {
			if (project.getEmployees().contains(employee)) {
				filteredEmployees.add(employee);
			}
		}
		employees = filteredEmployees;
	}

	@Override
	public String[] getOptions() {
		List<String> options = new ArrayList<String>();

		options.add("Back");

		for (Employee employee : employees) {
			options.add(String.format("[%s] %s", employee.getId(), employee.getName()));
		}

		return options.toArray(new String[0]);
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (index == 0) {
			appUI.setScreen(source);
		} else {
			Employee employee = employees.get(index - 1);

			callback.callback(this, out, employee);
		}
	}

	public void removeOption(Employee employee) {
		employees.remove(employee);
	}
}