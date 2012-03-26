package dk.softwarehuset.projectmanagement.app;

public class TooManyProjectsException extends Exception {
	public TooManyProjectsException(String message) {
		super(message);
	}
}