package com.example.employee.service;

import com.example.employee.dto.EmployeeRequestDTO;
import com.example.employee.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO getEmployee(int employeeId);

    EmployeeResponseDTO updateEmployee(int employeeId, EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO deleteEmployeeById(int employeeId);
}
