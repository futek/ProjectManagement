package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dk.softwarehuset.projectmanagement.app.Activity;
import dk.softwarehuset.projectmanagement.app.Employee;
import dk.softwarehuset.projectmanagement.app.InvalidArgumentException;
import dk.softwarehuset.projectmanagement.app.Project;

public class ViewProjectScreen extends MenuListScreen {
	private Screen source;
	private Project project;

	public ViewProjectScreen(ApplicationUI appUI, Screen source, Project project) {
		super(appUI);

		this.source = source;
		this.project = project;
	}

	@Override
	public String[] getOptions() {
		List<String> options = new ArrayList<String>();

		options.add("Back");

		boolean inProject = false;
		for (Employee employee : project.getEmployees()) {
			if (appUI.getApp().getCurrentEmployee().equals(employee)) {
				inProject = true;
				break;
			}
		}

		if (inProject) {
			// Current employee in project
			options.add("Leave project");

			if (project.getProjectLeader() == null) {
				// No project leader exists
				options.add("Register as project leader");
			} else if (project.getProjectLeader() == appUI.getApp().getCurrentEmployee()) {
				// Current employee is project leader
				options.add("Unregister as project leader");
				options.add("Create activity");
				options.add("Browse activities");
				options.add("Assign employees to activity");
				options.add("Edit project properties");
			}
		} else {
			// Current employee NOT in project
			options.add("Join project");
		}

		return options.toArray(new String[0]);
	}

	@Override
	public void optionSelected(int index, String option, PrintWriter out) {
		if (option.equals("Back")) {
			appUI.setScreen(source);
		} else if (option.equals("Join project")) {
			try {
				project.addEmployee(appUI.getApp().getCurrentEmployee());
				out.println("You've joined the project \"" + project.getName() + "\".");
			} catch (InvalidArgumentException e) {
				out.println(e.getMessage() + ".");
			}
		} else if (option.equals("Leave project")) {
			project.removeEmployee(appUI.getApp().getCurrentEmployee());
			out.println("You've left the project \"" + project.getName() + "\".");
		} else if (option.equals("Browse activities")) {
			SelectActivityDialog selectActivityDialog = new SelectActivityDialog(appUI, this, project, new Callback<Activity>() {
				@Override
				public void callback(Screen source, PrintWriter out, Activity activity) {
					appUI.setScreen(new ViewActivityScreen(appUI, source, activity));
				}
			});

			appUI.setScreen(selectActivityDialog);
		} else if (option.equals("Register as project leader")) {
			project.setProjectLeader(appUI.getApp().getCurrentEmployee());
			out.println("You're now project leader for the project \"" + project.getName() + "\".");
		} else if (option.equals("Unregister as project leader")) {
			project.setProjectLeader(null);
			out.println("You're no longer project leader for the project \"" + project.getName() + "\".");
		} else if (option.equals("Create activity")) {
			appUI.setScreen(new CreateActivityScreen(appUI, this, project));
		} else if (option.equals("Assign employees to activity")) {
			// Ask for an activity
			SelectActivityDialog selectActivityDialog = new SelectActivityDialog(appUI, this, project, new Callback<Activity>() {
				@Override
				public void callback(Screen source, PrintWriter out, final Activity activity) {
					// Ask for an employee on the project
					final SelectEmployeeDialog selectEmployeeDialog = new SelectEmployeeDialog(appUI, source, project, new Callback<Employee>() {
						@Override
						public void callback(Screen source, PrintWriter out, Employee employee) {
							try {
								// Add the employee to the activity
								employee.addActivity(activity);
							} catch (InvalidArgumentException e) {
								// Shouldn't happen
								out.printf(e.getMessage() + ".");
							}

							out.printf("Employee \"%s\" assigned to activity \"%s\".%n", employee.getName(), activity.getName());

							// Remove the option selected
							((SelectEmployeeDialog) source).removeOption(employee); // TODO: This cast is not optimal..
						}
					});

					appUI.setScreen(selectEmployeeDialog);
				}
			});

			appUI.setScreen(selectActivityDialog);
		} else if (option.equals("Edit project properties")) {
			appUI.setScreen(new EditProjectPropertiesScreen(appUI, this, project));
		}
	}
}