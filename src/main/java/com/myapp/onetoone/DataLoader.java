package com.myapp.onetoone;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@org.springframework.stereotype.Component
@AllArgsConstructor
public class DataLoader {
  // @autowired
  private AddressRepository addressRepository;
  private EmployeeRepository employeeRepository;
  // constructor
  
  @PostConstruct
  public void load(){
    // address
    addressRepository.deleteAll();
    Address address1 = new Address();
    Address address2 = new Address();
    // address1
    address1.setStreet("Street Sembawang Loop 45");
    address1.setCity("Sembawang Town Centre");
    address1.setState("Sembawang");
    // address2
    address2.setStreet("Street Punggol Waterway 21");
    address2.setCity("Punggol Waterway Centre");
    address2.setState("Punggol");    
    // employee
    employeeRepository.deleteAll();
    Employee employee1 = new Employee();
    Employee employee2 = new Employee();
    // employee1
    employee1.setEmployeeName("Tiny mouse robot");
    employee1.setEmployeeEmailId("tinymouse@example.com");
    employee1.setEmployeeMobile("1001");
    employee1.setEmployeeDesign("Mouse");
    employee1.setMappedByAddress(address1);
    // employee 2
    employee2.setEmployeeName("Super Bat Rider");
    employee2.setEmployeeEmailId("superbat@example.com");
    employee2.setEmployeeMobile("2002");
    employee2.setEmployeeDesign("Bat");
    employee2.setMappedByAddress(address2);
    // save employee to address
    address1.setOwnedByEmployee(employee1);
    address2.setOwnedByEmployee(employee2);
    // save to repo
    // address repo
    addressRepository.save(address1);
    addressRepository.save(address2);
    // employee repo
    employeeRepository.save(employee1);
    employeeRepository.save(employee2);
  }  
}
