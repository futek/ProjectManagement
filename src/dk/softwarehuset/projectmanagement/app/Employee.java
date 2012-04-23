package dk.softwarehuset.projectmanagement.app;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class Employee {
	private String id;
	private String name;
	private List<Activity> activities = new ArrayList<Activity>();

	public Employee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isAdmin() {
		return false;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public void registerTime(Activity activity, int duration) {
		LocalDate today = LocalDate.now(); // TODO: Use date server?

		duration += activity.getRegisteredTime(this, today);

		activity.setRegisteredTime(this, today, duration);
	}
}