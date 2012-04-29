package dk.softwarehuset.projectmanagement.app;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class Project {
	private String id;
	private String name;
	private Employee projectLeader;
	private List<Employee> employees = new ArrayList<Employee>();
	private List<Activity> activities = new ArrayList<Activity>();
	private Interval interval = new Interval();

	public Project(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee newEmployee) throws InvalidArgumentException {
		for (Employee employee : employees) {
			if (id.equals(employee.getId())) {
				throw new InvalidArgumentException("Employee on project already");
			}
		}

		employees.add(newEmployee);
	}

	public void removeEmployee(Employee employee) {
		if (getProjectLeader() == employee) {
			setProjectLeader(null);
		}

		employees.remove(employee);
	}

	public void setProjectLeader(Employee employee) {
		projectLeader = employee;
	}

	public Employee getProjectLeader() {
		return projectLeader;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void addActivity(Activity activity) throws IllegalStateException {
		if (activities.contains(activity)) {
			throw new IllegalStateException("Activity already added");
		}

		activities.add(activity);
	}

	public int getTotalRegisteredTime() {
		int total = 0;

		for (Activity activity : activities) {
			total += activity.getTotalRegisteredTime();
		}

		return total;
	}

	public void setStartDate(int weekYear, int weekNumber) throws InvalidArgumentException {
		interval.setStartDate(weekYear, weekNumber);
	}

	public void setEndDate(int weekYear, int weekNumber) throws InvalidArgumentException {
		interval.setEndDate(weekYear, weekNumber);
	}

	public LocalDate getStartDate() {
		return interval.getStartDate();
	}

	public LocalDate getEndDate() {
		return interval.getEndDate();
	}
}