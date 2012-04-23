package dk.softwarehuset.projectmanagement.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Application {
	private List<Employee> employees = new ArrayList<Employee>();
	private List<Project> projects = new ArrayList<Project>();
	private int annualProjectCounter = 0;
	private int yearOfLatestProject = 0;
	private Employee currentEmployee;
	private DateServer dateServer = new DateServer();

	public Application() {
		Admin admin = new Admin("ZZZZ", "Administrator", this);
		employees.add(admin);
	}

	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
	}

	public List<Project> getProjects() {
		return Collections.unmodifiableList(projects);
	}

	public Employee createEmployee(String id, String name) throws NonUniqueIdentifierException, PermissionDeniedException {
		if (currentEmployee == null) {
			throw new PermissionDeniedException("Not signed in");
		}

		if (!currentEmployee.isAdmin()) {
			throw new PermissionDeniedException("Not admin");
		}

		id = id.toUpperCase();

		for (Employee employee : employees) {
			if (id.equals(employee.getId())) {
				throw new NonUniqueIdentifierException("Duplicate employee id");
			}
		}

		Employee employee = new Employee(id, name, this);

		employees.add(employee);

		return employee;
	}

	public Project createProject(String name) throws TooManyProjectsException, PermissionDeniedException {
		if (currentEmployee == null) {
			throw new PermissionDeniedException("Not signed in");
		}

		int currentYear = dateServer.getDate().get(Calendar.YEAR);

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
		Activity activity = new Activity(name);

		project.addActivity(activity);

		return activity;
	}

	public Activity createActivity(Employee employee, String name) {
		Activity activity = new Activity(name);

		employee.addActivity(activity);

		return activity;
	}

	public void signIn(String id) throws WrongCredentialsException {
		id = id.toUpperCase();

		for (Employee employee : employees) {
			if (id.equals(employee.getId())) {
				currentEmployee = employee;

				return;
			}
		}

		throw new WrongCredentialsException("Wrong id");
	}

	public void signOut() {
		currentEmployee = null;
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public Employee getEmployeeById(String id) {
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
}