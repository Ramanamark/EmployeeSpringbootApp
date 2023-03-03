package com.example.employee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="Emp_TB")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long employeeId; //Employee ID
    @Column(name="FNAME")
    private String firstName;
    @Column(name="LNAME")
    private String lastName;
    @Column(name="DEPT")
    private String department;
    @Column(name="DESG")
    private String designation;
    @Column(name="SALARY")
    private double salary;

    public Employee(long employeeId, String firstName, String lastName, String department, String designation, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }


}
