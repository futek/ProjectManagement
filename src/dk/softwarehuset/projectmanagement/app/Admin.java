package dk.softwarehuset.projectmanagement.app;

public class Admin extends Employee {
	public Admin(Application app, String id, String name) {
		super(app, id, name);
	}

	@Override
	public boolean isAdmin() {
		return true;
	}
}