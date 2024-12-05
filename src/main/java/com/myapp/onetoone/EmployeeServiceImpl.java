package com.myapp.onetoone;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
  // fields
  private EmployeeRepository employeeRepository;
  // autowired constructor
  public EmployeeServiceImpl(EmployeeRepository employeeRepository){
    this.employeeRepository = employeeRepository;
  }
  // create an employee
  @Override
  public void save(Employee emp){
    employeeRepository.save(emp);
  };
  // list all employees
  @Override
  public List<Employee> findAll(){
    return employeeRepository.findAll();
  };
  // find by employee name
  @Override
  public List<Employee> findByEmployeeName(
    String employeeName
  ){
    List<Employee> results = employeeRepository.findByEmployeeNameContainsIgnoreCase(employeeName);
    return results;
  }
}
