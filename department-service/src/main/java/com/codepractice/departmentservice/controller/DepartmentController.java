package com.codepractice.departmentservice.controller;

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

import com.codepractice.departmentservice.client.EmployeeClient;
import com.codepractice.departmentservice.model.Department;
import com.codepractice.departmentservice.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeClient employeeClient;
	
	@PostMapping
	public Department add(@RequestBody Department department) {
		LOGGER.info("Department Add:{}",department);
		return departmentRepository.addDepartment(department);
	}
	
	@GetMapping
	public List<Department> findAll(){
		LOGGER.info("Department findall");
		return departmentRepository.finaAll();
	}
	
	@GetMapping("/{Id}")
	public Department findById(@PathVariable Long  Id) {
		LOGGER.info("Department FindById:{}",Id);
		return departmentRepository.findById(Id);
	}
	
	@GetMapping("/with-employees")
	public List<Department> findAllWithEmployees(){
		LOGGER.info("Department findall");
		List<Department>  departments =  departmentRepository.finaAll();
		
		departments.forEach(department-> department.setEmployees
				(employeeClient.findByDepartment(department.getId())));
		
		return departments;
	}
	

}
