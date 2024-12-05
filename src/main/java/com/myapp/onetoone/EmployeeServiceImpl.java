package com.myapp.onetoone;

import java.beans.Customizer;
import java.util.List;
import java.util.Optional;

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
  // list one employee
  @Override
  public Employee getOneEmployee(Long id){
    Optional<Employee> optionalEmployee = employeeRepository.findById(id);
    if(optionalEmployee.isPresent()){
      Employee foundEmployee = optionalEmployee.get();
      return foundEmployee;
    }
    else {
      return null;
    }
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
