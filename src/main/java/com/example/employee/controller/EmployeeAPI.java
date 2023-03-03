package com.example.employee.controller;

import com.example.employee.dto.EmployeeRequestDTO;
import com.example.employee.dto.EmployeeResponseDTO;
import com.example.employee.service.EmployeeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
public class EmployeeAPI {
    private static final Log LOGGER = LogFactory.getLog(EmployeeAPI.class);
    @Autowired
    private com.example.employee.service.EmployeeService EmployeeService;
    @GetMapping("/Employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        List<EmployeeResponseDTO> EmployeeList = EmployeeService.getAllEmployees();
        if(EmployeeList.size()>0){
            return new ResponseEntity<List<EmployeeResponseDTO>>(EmployeeList, HttpStatus.OK);
        }else{
            LOGGER.info("There is no Employees available");
            return new ResponseEntity<List<EmployeeResponseDTO>>(EmployeeList, HttpStatus.NO_CONTENT);
        }

    }
    @PostMapping("/Employees")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO EmployeeRequestDTO){
        EmployeeResponseDTO EmployeeResponseDTO =  EmployeeService.addEmployee(EmployeeRequestDTO);
        if(EmployeeResponseDTO!=null){
            return new ResponseEntity<EmployeeResponseDTO>(EmployeeResponseDTO,HttpStatus.CREATED);
        }
        return null;
    }
    @GetMapping("/Employees/{EmployeeId}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable int EmployeeId){
        return EmployeeService.getEmployee(EmployeeId);
    }
    @PutMapping("/Employees/{EmployeeId}")
    public EmployeeResponseDTO updateEmployeeById(@PathVariable int EmployeeId,@RequestBody  EmployeeRequestDTO EmployeeRequestDTO){
        return EmployeeService.updateEmployee(EmployeeId,EmployeeRequestDTO);
    }
    @DeleteMapping("/Employees/{EmployeeId}")
    public EmployeeResponseDTO deleteEmployeeById(@PathVariable int EmployeeId){
        return EmployeeService.deleteEmployeeById(EmployeeId);
    }
}
