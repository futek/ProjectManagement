package dk.softwarehuset.projectmanagement.app;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

public class Activity {
	private String name;
	private Week startDate;
	private Week endDate;
	private Map<Employee, Map<LocalDate, Integer>> registeredTime = new HashMap<Employee, Map<LocalDate, Integer>>();

	public Activity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(Week startDate) throws IllegalArgumentException {
		if (endDate != null && startDate.compareTo(endDate) == 1) {
			throw new IllegalArgumentException("Start date after end date");
		}

		this.startDate = startDate;
	}

	public void setEndDate(Week endDate) throws IllegalArgumentException {
		if (startDate != null && endDate.compareTo(startDate) == -1) {
			throw new IllegalArgumentException("End date before start date");
		}

		this.endDate = endDate;
	}

	public Week getStartDate() {
		return startDate;
	}

	public Week getEndDate() {
		return endDate;
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
}