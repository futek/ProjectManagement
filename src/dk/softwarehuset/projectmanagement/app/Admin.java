package dk.softwarehuset.projectmanagement.app;

public class Admin extends Employee {
	public Admin(String id, String name) {
		super(id, name);
	}
	
	@Override
	public boolean isAdmin() {
		return true;
	}
}