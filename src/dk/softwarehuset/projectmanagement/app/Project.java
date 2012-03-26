package dk.softwarehuset.projectmanagement.app;

public class Project {
	private String name;

	public Project(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;	
	}
}