package dk.softwarehuset.projectmanagement.app;

public class PermissionDeniedException extends Exception {
	public PermissionDeniedException(String message) {
		super(message);
	}
}