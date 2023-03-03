package com.example.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeRequestDTO {

    private String firstName;
    private String lastName;
    private String department;
    private String designation;
    private double salary;

}
