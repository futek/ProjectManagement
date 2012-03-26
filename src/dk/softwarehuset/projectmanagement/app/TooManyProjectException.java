package dk.softwarehuset.projectmanagement.app;

public class TooManyProjectException extends Exception {
	public TooManyProjectException(String message) {
		super(message);
	}
}