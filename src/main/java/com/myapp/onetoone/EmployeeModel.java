package com.myapp.onetoone;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//@Entity
//@Table(name = "employee_model")
public class EmployeeModel {
  //@Id
  //@GenerateValue(strategy = GenerationType.IDENTITY);
  private Long employeeModelId;
  private String employeeModelName;
  private String employeeModelEmailId;
  private String employeeModelMobile;
  private String employeeModelDesign;
  private String employeeModelStreet;
  private String employeeModelCity;
  private String employeeModelState;
}
