package dk.softwarehuset.projectmanagement.app;

public class Week implements Comparable<Week> {
	private int year;
	private int weekNumber;

	public Week(int year, int weekNumber) throws IllegalArgumentException {
		if (weekNumber < 1 || weekNumber > 54) {
			throw new IllegalArgumentException("Invalid week number");
		}

		this.year = year;
		this.weekNumber = weekNumber;
	}

	public int getYear() {
		return year;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	@Override
	public int compareTo(Week other) {
		if (this.getYear() > other.getYear()) {
			return 1;
		} else if (this.getYear() < other.getYear()) {
			return -1;
		} else {
			if (this.getWeekNumber() > other.getWeekNumber()) {
				return 1;
			} else if (this.getWeekNumber() < other.getWeekNumber()) {
				return -1;
			}
		}
		
		return 0;
	}
}