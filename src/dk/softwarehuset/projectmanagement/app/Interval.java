package dk.softwarehuset.projectmanagement.app;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;

public class Interval {
	private LocalDate startDate;
	private LocalDate endDate;

	public void setStartDate(int weekYear, int weekNumber) throws InvalidArgumentException {
		LocalDate newStartDate;

		try {
			newStartDate = (new LocalDate()).withDayOfWeek(1).withWeekyear(weekYear).withWeekOfWeekyear(weekNumber);
		} catch (IllegalFieldValueException e) {
			throw new InvalidArgumentException("Invalid week number for week year " + weekYear);
		}

		if (endDate != null && newStartDate.compareTo(endDate) == 1) {
			throw new InvalidArgumentException("Start date after end date");
		}

		startDate = newStartDate;
	}

	public void setEndDate(int weekYear, int weekNumber) throws InvalidArgumentException {
		LocalDate newEndDate;

		try {
			newEndDate = (new LocalDate()).withDayOfWeek(1).withWeekyear(weekYear).withWeekOfWeekyear(weekNumber);
		} catch (IllegalFieldValueException e) {
			throw new InvalidArgumentException("Invalid week number for week year " + weekYear);
		}

		if (startDate != null && newEndDate.compareTo(startDate) == -1) {
			throw new InvalidArgumentException("End date before start date");
		}

		endDate = newEndDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
}