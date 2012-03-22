package dk.softwarehuset.projectmanagement.app;

public class WrongCredentialsException extends Exception {
	public WrongCredentialsException(String message) {
		super(message);
	}
}