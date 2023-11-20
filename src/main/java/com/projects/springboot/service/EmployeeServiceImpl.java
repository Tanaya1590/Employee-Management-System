package com.projects.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.projects.springboot.models.Employee;
import com.projects.springboot.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepo.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepo.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepo.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepo.deleteById(id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
		return this.employeeRepo.findAll(pageable);
	}

}
