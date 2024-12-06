package com.myapp.onetoone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@WebMvcTest(EmployeeController.class)
@SpringBootTest
@AutoConfigureMockMvc 
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

  /* Test the test */
  // @Test
  // public void test1(){
  //   assertEquals(1, 1, "message: true");
  // }

  // Autowired fields
  @Autowired
  private MockMvc mockie;

  // object mapper to create json string
  @Autowired
  private ObjectMapper objectMapper;


  // mock a repository
  // then inject the mock repository into the service
  /*
  @Mock
  private EmployeeRepository employeeRepository;
  @InjectMocks
  EmployeeServiceImpl employeeService;  
  
  @Mock
  private AddressRepository addressRepository;
  @InjectMocks
  AddressServiceImpl addressService;
  */

  // GET one employee - OK
  @DisplayName("Get employee by id - ok")
  @Test
  public void testGetOneEmployee_Success() throws Exception {
    // create a sample

    // 1. get the request uri
    // IMPORTANT: The problem is with the generated id. Need to check the generated id before
    // running the test.
    // Check the value against something permanent, i.e. something that we set like name.
    RequestBuilder request = MockMvcRequestBuilders.get("/api/employees/2");
    // 2. perform the request and get response
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.status").value(1))
      .andExpect(jsonPath("$.data.employeeModelName"). hasJsonPath())
      .andExpect(jsonPath("$.data.employeeModelName")
        .value("Tiny mouse robot"));     
  }

    // GET one employee - No such employee
    @DisplayName("Get employee by id - no such employee id")
    @Test
    public void testGetOneEmployee_Fail() throws Exception {
      // create a sample
  
      // 1. get the request uri
      RequestBuilder request = MockMvcRequestBuilders.get("/api/employees/99");
      // 2. perform the request and get response
      mockie.perform(request)
        .andDo(print())
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$.status").value(0));
    }

  // Get all employees
  @DisplayName("Get all employee")
  @Test
  public void getAllEmployees() throws Exception {
    // Build the request
    RequestBuilder request = MockMvcRequestBuilders.get("/api/employees");
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.status").value(1));
  }
  
  // Create an employee
  @DisplayName("Create an employee")
  @Test
  public void testCreateEmployee_Success() throws Exception {
    // Create an employee 
    EmployeeModel empModel = new EmployeeModel();
    empModel.setEmployeeModelId(1001L);
    empModel.setEmployeeModelName("Employee 1");
    empModel.setEmployeeModelEmailId("employee1@example.com");
    empModel.setEmployeeModelMobile("10001000");
    empModel.setEmployeeModelDesign("Design 1");
    empModel.setEmployeeModelStreet("Street 1");
    empModel.setEmployeeModelCity("City 1");
    empModel.setEmployeeModelState("State 1");
    // Convert to Json object
    String newEmployeeJson = objectMapper.writeValueAsString(empModel);
    // build the request
    RequestBuilder request = MockMvcRequestBuilders
      .post("/api/save")
      .contentType(MediaType.APPLICATION_JSON)
      .content(newEmployeeJson);
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.status").value(1)); // 1 - OK
  }

  // search for employee's name
  @Test
  public void searchEmployeeNameTest() throws Exception{
    // build the request
    RequestBuilder request = MockMvcRequestBuilders
      .get("/api/search?name=tiny mouse robot");
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.data.[0].employeeModelName").hasJsonPath())
      .andExpect(jsonPath("$.data.[0].employeeModelName").value("Tiny mouse robot"));
  }

} // END OF CLASS
