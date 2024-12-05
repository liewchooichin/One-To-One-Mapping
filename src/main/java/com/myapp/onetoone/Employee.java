package com.myapp.onetoone;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Entity
@Table(name="employee")
public class Employee {
  // primary key
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="employee_id")
  private Long employeeId;

  // other fields
  @Column(name = "employee_name")
  private String employeeName;

  @Column(name = "employee_email_id")
  private String employeeEmailId;

  @Column(name = "employee_mobile")
  private String employeeMobile;

  @Column(name = "employee_design")
  private String employeeDesign;

  // relate to Address
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address mappedByAddress;

}
