package com.example.employee.dto;

import com.example.employee.model.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeResponseDTO {
    private long employeeId;
    private String firstName;
    private String lastName;
    private String department;
    private String designation;
    private double salary;

    public EmployeeResponseDTO(Employee employee){
        this.setEmployeeId(employee.getEmployeeId());
        this.setFirstName(employee.getFirstName());
        this.setLastName(employee.getLastName());
        this.setDepartment(employee.getDepartment());
        this.setDesignation(employee.getDesignation());
        this.setSalary(employee.getSalary());
    }
}
