package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DAO.EmployeeInterface;
import com.example.demo.Model.Employee;

@Service
public class Employeeservice {
	
	private EmployeeInterface repository; //initialising the variable of the type
	
	public Employeeservice(EmployeeInterface repository) {
		//creating a constructor and passing data from repo to here and 
		//retrieving the data as a list.
		this.repository = repository; //assigning parameter value to local variable		
	}
	
	//getting all the employee data
	public List<Employee> getAllEmployee(){
		return repository.findAll();
	}
	
	//getting specific employee data
	public Employee getEmployeeById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	//To add data & saving those details in repo
	public Employee SaveEmployee(Employee emp) {
		return repository.save(emp);
			
	}
	//to delete the data by id
	public void DeleteEmployee(Long id) {
		 repository.deleteById(id);
			
	}
}
