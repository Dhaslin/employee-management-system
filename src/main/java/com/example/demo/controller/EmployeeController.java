package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Employee;
import com.example.demo.Service.Employeeservice;

@Controller 
@RequestMapping("/employees")
public class EmployeeController {
	
	private Employeeservice Service;
	
	public EmployeeController(Employeeservice Service) {
		this.Service=Service;
	}
	
	@GetMapping //don't add ("/")
	public String ShowAllEmployees(Model model) { //Model type is provided by spring itself
		model.addAttribute("employees", Service.getAllEmployee());//no form binding here so no new object creation
		return "list";
	}
	
	@GetMapping("/add")
	public String addEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee()); //for form binding we're creating new object.
		return "add";		
	}
	
	@PostMapping("/add")
	public String saveEmployee(@ModelAttribute Employee employee) {
		Service.SaveEmployee(employee);
		return "redirect:/employees";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editEmployeeForm(@PathVariable Long id,Model model)
	{
		model.addAttribute("employee",Service.getEmployeeById(id));
		return "edit";
		
	}
	
	@PostMapping("/edit/{id}")
	public String updateEmployee(@PathVariable Long id,@ModelAttribute Employee employee) {
		
		employee.setEmpId(id);
		Service.SaveEmployee(employee);
		return "redirect:/employees";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		
		Service.DeleteEmployee(id);
		return "redirect:/employees";
		
	}
}
