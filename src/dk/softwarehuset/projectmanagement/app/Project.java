package dk.softwarehuset.projectmanagement.app;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private String id;
	private String name;
	private List<Employee> employees = new ArrayList<Employee>();

	public Project(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee newEmployee) throws NonUniqueIdentifierException {
		for (Employee employee : employees) {
			if (id.equals(employee.getId())) {
				throw new NonUniqueIdentifierException("Employee on project already");
			}
		}
		
		employees.add(newEmployee);
	}

	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}
}