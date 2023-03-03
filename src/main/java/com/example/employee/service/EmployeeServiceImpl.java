package com.example.employee.service;

import com.example.employee.dto.EmployeeRequestDTO;
import com.example.employee.dto.EmployeeResponseDTO;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> EmployeeList = employeeRepo.findAll();
        List<EmployeeResponseDTO> EmployeeResponseDTOList = new ArrayList<EmployeeResponseDTO>();
        EmployeeResponseDTOList =  EmployeeList.stream().map((Employee)->{
            EmployeeResponseDTO EmployeeResponseDTO = new EmployeeResponseDTO();
            EmployeeResponseDTO.setEmployeeId(Employee.getEmployeeId());
            EmployeeResponseDTO.setFirstName(Employee.getFirstName());
            EmployeeResponseDTO.setLastName(Employee.getLastName());
            EmployeeResponseDTO.setDepartment(Employee.getDepartment());
            EmployeeResponseDTO.setDesignation(Employee.getDesignation());
            EmployeeResponseDTO.setSalary(Employee.getSalary());
            return EmployeeResponseDTO;
        }).collect(Collectors.toList());
        return EmployeeResponseDTOList;
    }

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequestDTO.getFirstName());
        employee.setLastName(employeeRequestDTO.getLastName());
        employee.setDepartment(employeeRequestDTO.getDepartment());
        employee.setDesignation(employeeRequestDTO.getDesignation());
        employee.setSalary(employeeRequestDTO.getSalary());
        Employee savedEmployee =  employeeRepo.save(employee);
        EmployeeResponseDTO EmployeeResponseDTO = new EmployeeResponseDTO();
        EmployeeResponseDTO.setEmployeeId(savedEmployee.getEmployeeId());
        EmployeeResponseDTO.setFirstName(savedEmployee.getFirstName());
        EmployeeResponseDTO.setLastName(savedEmployee.getLastName());
        EmployeeResponseDTO.setDepartment(savedEmployee.getDepartment());
        EmployeeResponseDTO.setDesignation(savedEmployee.getDesignation());
        EmployeeResponseDTO.setSalary(savedEmployee.getSalary());
        return EmployeeResponseDTO;
    }

    @Override
    public EmployeeResponseDTO getEmployee(int employeeId) {
        Optional<Employee> EmployeeOptional = employeeRepo.findById(employeeId);

        if(EmployeeOptional.isPresent()){
            Employee searchedEmployee =  EmployeeOptional.get();
            EmployeeResponseDTO EmployeeResponseDTO = new EmployeeResponseDTO();
            EmployeeResponseDTO.setEmployeeId(searchedEmployee.getEmployeeId());
            EmployeeResponseDTO.setFirstName(searchedEmployee.getFirstName());
            EmployeeResponseDTO.setLastName(searchedEmployee.getLastName());
            EmployeeResponseDTO.setDepartment(searchedEmployee.getDepartment());
            EmployeeResponseDTO.setDesignation(searchedEmployee.getDesignation());
            EmployeeResponseDTO.setSalary(searchedEmployee.getSalary());
            return EmployeeResponseDTO;
        }else{
            throw new EmployeeNotFoundException("The Employee with ID : "+employeeId + " is not present.");
        }
    }

    @Override
    public EmployeeResponseDTO updateEmployee(int employeeId, EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> EmployeeOptional = employeeRepo.findById(employeeId);
        if(EmployeeOptional.isPresent()){
            //If present then updation will happen
            Employee searchedEmployee = EmployeeOptional.get();
            searchedEmployee.setFirstName(employeeRequestDTO.getFirstName());
            searchedEmployee.setLastName(employeeRequestDTO.getLastName());
            searchedEmployee.setDepartment(employeeRequestDTO.getDepartment());
            searchedEmployee.setDesignation(employeeRequestDTO.getDesignation());
            searchedEmployee.setSalary(employeeRequestDTO.getSalary());
            employeeRepo.flush();
            EmployeeResponseDTO EmployeeResponseDTO = new EmployeeResponseDTO();
            EmployeeResponseDTO.setEmployeeId(searchedEmployee.getEmployeeId());
            EmployeeResponseDTO.setFirstName(searchedEmployee.getFirstName());
            EmployeeResponseDTO.setLastName(searchedEmployee.getLastName());
            EmployeeResponseDTO.setDepartment(searchedEmployee.getDepartment());
            EmployeeResponseDTO.setDesignation(searchedEmployee.getDesignation());
            EmployeeResponseDTO.setSalary(searchedEmployee.getSalary());
            return EmployeeResponseDTO;
        }else{
            //A new Employee will be created
            Employee newEmployee = new Employee();
            newEmployee.setFirstName(employeeRequestDTO.getFirstName());
            newEmployee.setLastName(employeeRequestDTO.getLastName());
            newEmployee.setDepartment(employeeRequestDTO.getDepartment());
            newEmployee.setDesignation(employeeRequestDTO.getDesignation());
            newEmployee.setSalary(employeeRequestDTO.getSalary());
            Employee savedEmployee = employeeRepo.save(newEmployee);
            EmployeeResponseDTO EmployeeResponseDTO = new EmployeeResponseDTO();
            EmployeeResponseDTO.setEmployeeId(savedEmployee.getEmployeeId());
            EmployeeResponseDTO.setFirstName(savedEmployee.getFirstName());
            EmployeeResponseDTO.setLastName(savedEmployee.getLastName());
            EmployeeResponseDTO.setDepartment(savedEmployee.getDepartment());
            EmployeeResponseDTO.setDesignation(savedEmployee.getDesignation());
            EmployeeResponseDTO.setSalary(savedEmployee.getSalary());
            return EmployeeResponseDTO;
        }
    }

    @Override
    public EmployeeResponseDTO deleteEmployeeById(int employeeId) {
        Optional<Employee> EmployeeOptional = employeeRepo.findById(employeeId);
        if(EmployeeOptional.isPresent()){
            Employee deletedEmployee = EmployeeOptional.get();
            employeeRepo.delete(deletedEmployee);
            EmployeeResponseDTO EmployeeResponseDTO = new EmployeeResponseDTO(deletedEmployee);
            return EmployeeResponseDTO;
        }else{
            throw new EmployeeNotFoundException("Employee having ID : "+employeeId+ " not present in DB");
        }

    
    }
}
