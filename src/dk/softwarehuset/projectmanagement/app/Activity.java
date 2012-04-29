package dk.softwarehuset.projectmanagement.app;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

public class Activity {
	private String name;
	private Map<Employee, Map<LocalDate, Integer>> registeredTime = new HashMap<Employee, Map<LocalDate, Integer>>();
	private Interval interval = new Interval();

	public Activity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegisteredTime(Employee employee, LocalDate date, int duration) {
		if (duration < 0) {
			throw new IllegalArgumentException("Negative duration");
		}

		Map<LocalDate, Integer> map;

		if (!registeredTime.containsKey(employee)) {
			map = new HashMap<LocalDate, Integer>();
		} else {
			map = registeredTime.get(employee);
		}

		if (duration > 0) {
			map.put(date, duration);
		} else {
			map.remove(date);
		}

		registeredTime.put(employee, map);
	}

	public int getRegisteredTime(Employee employee, LocalDate date) {
		if (!registeredTime.containsKey(employee)) {
			return 0;
		}

		Map<LocalDate, Integer> map = registeredTime.get(employee);

		if (!map.containsKey(date)) {
			return 0;
		}

		return map.get(date);
	}

	public int getTotalRegisteredTime() {
		int totalDuration = 0;

		for (Map<LocalDate, Integer> map : registeredTime.values()) {
			for (int duration : map.values()) {
				totalDuration += duration;
			}
		}

		return totalDuration;
	}

	public int getTotalRegisteredTime(Employee employee) {
		int totalDuration = 0;

		Map<LocalDate, Integer> map = registeredTime.get(employee);

		if (map == null) {
			return totalDuration;
		}

		for (int duration : map.values()) {
			totalDuration += duration;
		}

		return totalDuration;
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