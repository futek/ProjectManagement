package dk.softwarehuset.projectmanagement.app;

public class Activity {
	private String name;
	private Week startDate;
	private Week endDate;

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
}