package dk.softwarehuset.projectmanagement.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Application {
	private Map<String, Employee> employees = new HashMap<String, Employee>();
	private Map<String, Project> projects = new HashMap<String, Project>();
	private Employee currentEmployee;
	private DateServer dateServer = new DateServer();

	public Application() {
		Admin admin = new Admin("ZZZZ", "Administrator");
		employees.put(admin.getId(), admin);
	}
	
	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void SignIn(String id) throws WrongCredentialsException {
		if (!employees.containsKey(id)) {
			throw new WrongCredentialsException("No employee with that identifier");
		}
		
		currentEmployee = employees.get(id);
	}

	public void SignOut() {
		currentEmployee = null;
	}

	public Map<String, Employee> getEmployees() {
		return Collections.unmodifiableMap(employees);
	}

	public void addEmployee(Employee employee) throws PermissionDeniedException, NonUniqueIdentifierException {
		if (getCurrentEmployee() == null) {
			throw new PermissionDeniedException("Not signed in");
		}
		
		if (!getCurrentEmployee().isAdmin()) {
			throw new PermissionDeniedException("Not admin");
		}
		
		if (employees.containsKey(employee.getId())) {
			throw new NonUniqueIdentifierException("Employee id not unique");
		}
		
		employees.put(employee.getId(), employee);
	}

	public void addProject(Project project) throws PermissionDeniedException {
		if (getCurrentEmployee() == null) {
			throw new PermissionDeniedException("Not signed in");
		}
		
		if (!getCurrentEmployee().isAdmin()) {
			throw new PermissionDeniedException("Not admin");
		}
		
		projects.put(project.getName(), project);
		
	}
	
	public Map<String, Project> getProjects() {
		return Collections.unmodifiableMap(projects);
	}

	public void setDateServer(DateServer dateServer) {
		this.dateServer = new DateServer();
		
	}
}