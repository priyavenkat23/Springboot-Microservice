package com.codepractice.employeeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.codepractice.employeeservice.model.Employee;
import com.codepractice.employeeservice.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeRepository employeeRepository;

	
	@PostMapping
	public Employee add(@RequestBody Employee employee) {
		LOGGER.info("Employee Add:{}",employee);
		return employeeRepository.add(employee);
	}
	
	@GetMapping
	public List<Employee> findAll(){
		LOGGER.info("Employee findall");
		return employeeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable("id") Long  id) {
		LOGGER.info("Employee FindById:{}",id);
		return employeeRepository.findById(id);
	}
	
	@GetMapping("/department/{departmentId}")	
	public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId){
		LOGGER.info("Employee FindByDepartmentId:{}",departmentId);
		return employeeRepository.findByDepartment(departmentId);
	}
	
}
