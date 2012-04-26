package dk.softwarehuset.projectmanagement.app;

import org.joda.time.LocalDate;

public class DateServer {
	public LocalDate getDate() {
		return LocalDate.now();
	}
}