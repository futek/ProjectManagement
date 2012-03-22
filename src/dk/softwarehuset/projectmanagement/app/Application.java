package dk.softwarehuset.projectmanagement.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Application {
	private Map<String, Employee> employees = new HashMap<String, Employee>();
	private Employee currentEmployee;

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
			throw new NonUniqueIdentifierException("Not admin");
		}
		
		employees.put(employee.getId(), employee);
	}
}