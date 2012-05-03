package dk.softwarehuset.projectmanagement.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;

public class Application {
	private List<Employee> employees = new ArrayList<Employee>();
	private List<Project> projects = new ArrayList<Project>();
	private int annualProjectCounter = 0;
	private int yearOfLatestProject = 0;
	private Employee currentEmployee;
	private DateServer dateServer = new DateServer();

	public Application() {
		Admin admin = new Admin(this, "ZZZZ", "Administrator");
		employees.add(admin);
	}

	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
	}

	public List<Project> getProjects() {
		return Collections.unmodifiableList(projects);
	}

	public Employee createEmployee(String id, String name) throws PermissionDeniedException, InvalidArgumentException {
		if (currentEmployee == null) {
			throw new PermissionDeniedException("Not signed in");
		}

		if (!currentEmployee.isAdmin()) {
			throw new PermissionDeniedException("Not admin");
		}

		id = id.toUpperCase();

		// Validate id
		Employee.validateId(id);

		if (getEmployeeById(id) != null) {
			throw new InvalidArgumentException("Id already taken");
		}

		// Validate name
		if (name.length() == 0) {
			throw new InvalidArgumentException("No name given");
		}

		Employee employee = new Employee(this, id, name);

		employees.add(employee);

		return employee;
	}

	public Project createProject(String name) throws TooManyProjectsException, PermissionDeniedException {
		if (currentEmployee == null) {
			throw new PermissionDeniedException("Not signed in");
		}

		int currentYear = getDate().getYear();

		if (yearOfLatestProject != currentYear) {
			annualProjectCounter = 0;
		}

		if (annualProjectCounter >= 9999) {
			throw new TooManyProjectsException("Limit of 9999 projects reached, wait until new year");
		}

		annualProjectCounter++;

		String id = String.format("%02d%04d", currentYear % 100, annualProjectCounter);
		Project project = new Project(id, name);

		projects.add(project);

		yearOfLatestProject = currentYear;

		return project;
	}

	public Activity createActivity(Project project, String name) {
		Activity activity = new Activity(name, false);

		try {
			project.addActivity(activity);
		} catch (InvalidArgumentException e) {
			// The activity was just created above
			// This will never throw "Activity already added"
		}

		return activity;
	}

	public Activity createActivity(Employee employee, String name) {
		Activity activity = new Activity(name, true);

		try {
			employee.addActivity(activity);
		} catch (InvalidArgumentException e) {
			// The activity was just created above
			// This will never throw "Activity already added"
		}

		return activity;
	}

	public void signIn(String id) throws InvalidArgumentException {
		id = id.toUpperCase();

		Employee.validateId(id);

		Employee employee = getEmployeeById(id);

		if (employee == null) {
			throw new InvalidArgumentException("Unknown id");
		}

		currentEmployee = employee;
	}

	public void signOut() {
		currentEmployee = null;
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public Employee getEmployeeById(String id) {
		id = id.toUpperCase();

		for (Employee employee : employees) {
			if (id.equals(employee.getId())) {
				return employee;
			}
		}

		return null;
	}

	public Project getProjectById(String id) {
		for (Project project : projects) {
			if (id.equals(project.getId())) {
				return project;
			}
		}

		return null;
	}

	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public LocalDate getDate() {
		return dateServer.getDate();
	}
}