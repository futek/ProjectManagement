package dk.softwarehuset.projectmanagement.app;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class Employee {
	private String id;
	private String name;
	private List<Activity> activities = new ArrayList<Activity>();
	private Application app;

	public Employee(Application app, String id, String name) {
		this.app = app;
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

	public void addActivity(Activity activity) throws IllegalStateException {
		if (activities.contains(activity)) {
			throw new IllegalStateException("Activity already added");
		}

		activities.add(activity);
	}

	public void registerTime(Activity activity, int duration) {
		LocalDate today = app.getDate();

		duration += activity.getRegisteredTime(this, today);

		activity.setRegisteredTime(this, today, duration);
	}

	public int getTotalRegisteredTime() {
		int total = 0;

		for (Activity activity : activities) {
			total += activity.getTotalRegisteredTime(this);
		}

		return total;
	}
}