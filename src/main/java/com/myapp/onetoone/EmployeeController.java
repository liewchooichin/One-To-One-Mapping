package com.myapp.onetoone;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api")
public class EmployeeController {
  // fields
  private AddressService addressService;
  private EmployeeService employeeService;

  // constructor
  public EmployeeController(
    AddressService addressService,
    EmployeeService employeeService
  ) {
    this.addressService = addressService;
    this.employeeService = employeeService;
  }

  // create save
  @PostMapping("/save")
  public ResponseEntity<?> saveEmployee(
      @RequestBody EmployeeModel empModel) {
    Map<String, Object> model = new LinkedHashMap<>();
    // create address object and setting properties
    Address address = new Address();
    address.setStreet(empModel.getEmployeeModelStreet());
    address.setCity(empModel.getEmployeeModelCity());
    address.setState(empModel.getEmployeeModelState());
    // create employee object and setting properties
    Employee employee = new Employee();
    employee.setEmployeeName(empModel.getEmployeeModelName());
    employee.setEmployeeEmailId(empModel.getEmployeeModelEmailId());
    employee.setEmployeeMobile(empModel.getEmployeeModelMobile());
    employee.setEmployeeDesign(empModel.getEmployeeModelDesign());
    employee.setMappedByAddress(address); // set the address element
    // save the employee into the db
    employeeService.save(employee);
    model.put("status", 1);
    model.put("message", "Record is saved successfully.");
    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  // get all employees
  @GetMapping("/employees")
  public ResponseEntity<?> getEmployees() {
    Map<String, Object> model = new LinkedHashMap<>();
    List<Employee> empList = employeeService.findAll();
    List<EmployeeModel> empModelList = new ArrayList<>();
    // iterate the results if the results are not empty
    if(!empList.isEmpty()){
      for(Employee emp: empList){
        EmployeeModel empModel = new EmployeeModel();
        empModel.setEmployeeModelId(emp.getEmployeeId());
        empModel.setEmployeeModelName(emp.getEmployeeName());
        empModel.setEmployeeModelEmailId(emp.getEmployeeEmailId());
        empModel.setEmployeeModelMobile(emp.getEmployeeMobile());
        empModel.setEmployeeModelDesign(emp.getEmployeeDesign());
        empModel.setEmployeeModelDesign(emp.getEmployeeDesign());
        empModel.setEmployeeModelStreet(emp.getMappedByAddress().getStreet());
        empModel.setEmployeeModelCity(emp.getMappedByAddress().getCity());
        empModel.setEmployeeModelState(emp.getMappedByAddress().getState());
        empModelList.add(empModel);
      }
      // prepare the response
      model.put("status", 1);
      model.put("data", empModelList);
      return new ResponseEntity<>(model, HttpStatus.OK);
    } 
    // results are empty
    else {
      model.clear();
      model.put("status", 0);
      model.put("message", "Data is not found.");
      return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }
  }

  // get address list
  @GetMapping("/address-list")
  public ResponseEntity<?> getAddressList() {
      Map<String, Object> model = new LinkedHashMap<>();
      List<Address> addressList = addressService.findAll();
      List <EmployeeModel> employeeModelList = new ArrayList<>();
      // if address list is not empty, iterate the items.
      if(!addressList.isEmpty()){
        for(Address address: addressList){
          EmployeeModel empModel = new EmployeeModel();
          empModel.setEmployeeModelId(address.getOwnedByEmployee().getEmployeeId());
          empModel.setEmployeeModelName(address.getOwnedByEmployee().getEmployeeName());
          empModel.setEmployeeModelEmailId(address.getOwnedByEmployee().getEmployeeEmailId());
          empModel.setEmployeeModelMobile(address.getOwnedByEmployee().getEmployeeMobile());
          empModel.setEmployeeModelDesign(address.getOwnedByEmployee().getEmployeeDesign());
          empModel.setEmployeeModelStreet(address.getStreet());
          empModel.setEmployeeModelCity(address.getCity());
          empModel.setEmployeeModelState(address.getState());
          employeeModelList.add(empModel);
        }
        // prepare the response
        model.put("status", 1);
        model.put("data", employeeModelList);
        return new ResponseEntity<>(model, HttpStatus.OK);
      }
      // results are empty
      else {
        model.clear();
        model.put("status", 0);
        model.put("message", "Data is not found.");
        return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
      }
  }
  // find one employee by id
  @GetMapping("/employees/{id}")
  public ResponseEntity<?> getOneEmployee(@PathVariable Long id){
    Map<String, Object> model = new LinkedHashMap<>();
    Employee emp = employeeService.getOneEmployee(id);
    if(emp!=null){
      EmployeeModel empModel = new EmployeeModel();
      empModel.setEmployeeModelId(emp.getEmployeeId());
      empModel.setEmployeeModelName(emp.getEmployeeName());
      empModel.setEmployeeModelEmailId(emp.getEmployeeEmailId());
      empModel.setEmployeeModelMobile(emp.getEmployeeMobile());
      empModel.setEmployeeModelDesign(emp.getEmployeeDesign());
      empModel.setEmployeeModelStreet(emp.getMappedByAddress().getStreet());
      empModel.setEmployeeModelCity(emp.getMappedByAddress().getCity());
      empModel.setEmployeeModelState(emp.getMappedByAddress().getState());
      // set the response to this found employee
      model.put("status", 1);
      model.put("data", empModel);
      return new ResponseEntity<>(model, HttpStatus.OK);
    }
    // else no result
    else{
      model.clear();
      model.put("status", 0);
      model.put("message", "No results found.");
      return new ResponseEntity<>(model, HttpStatus.NO_CONTENT);
    }
  }
  // find employee name containing xxx
  @GetMapping("/search")
  public ResponseEntity<?> findByEmployeeName(
    @RequestParam String name
  ){
    Map<String, Object> model = new LinkedHashMap<>();
    List<Employee> employeeList = employeeService.findByEmployeeName(name);
    List <EmployeeModel> employeeModelList = new ArrayList<>();
    if(!employeeList.isEmpty()){
      // prepare the response
      for(Employee emp: employeeList){
        EmployeeModel empModel = new EmployeeModel();
        empModel.setEmployeeModelId(emp.getEmployeeId());
        empModel.setEmployeeModelName(emp.getEmployeeName());
        empModel.setEmployeeModelEmailId(emp.getEmployeeEmailId());
        empModel.setEmployeeModelMobile(emp.getEmployeeMobile());
        empModel.setEmployeeModelDesign(emp.getEmployeeDesign());
        empModel.setEmployeeModelStreet(emp.getMappedByAddress().getStreet());
        empModel.setEmployeeModelCity(emp.getMappedByAddress().getCity());
        empModel.setEmployeeModelState(emp.getMappedByAddress().getState());
        employeeModelList.add(empModel);
      }
      model.put("status", 1);
      model.put("data", employeeModelList);
      return new ResponseEntity<>(model, HttpStatus.OK);
    }
    // results are empty
    else {
      model.clear();
      model.put("status", 0);
      model.put("message", "No results found.");
      return new ResponseEntity<>(model, HttpStatus.NO_CONTENT);
    }
  }


} // END OF CLASS
