package dk.softwarehuset.projectmanagement.app;

public class Admin extends Employee {
	public Admin(String id, String name, Application app) {
		super(id, name, app);
	}

	@Override
	public boolean isAdmin() {
		return true;
	}
}