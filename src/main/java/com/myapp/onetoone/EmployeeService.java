package com.myapp.onetoone;

import java.util.List;

public interface EmployeeService {
  void save(Employee emp);
  List<Employee> findAll();
  List<Employee> findByEmployeeName(String employeeName);
}
