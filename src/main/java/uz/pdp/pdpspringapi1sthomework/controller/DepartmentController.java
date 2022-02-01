package uz.pdp.pdpspringapi1sthomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspringapi1sthomework.entity.Department;
import uz.pdp.pdpspringapi1sthomework.payload.ApiResponse;
import uz.pdp.pdpspringapi1sthomework.payload.CompanyDto;
import uz.pdp.pdpspringapi1sthomework.payload.DepartmentDto;
import uz.pdp.pdpspringapi1sthomework.service.DepartmentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse response = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editDepartment(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse response = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(response);
    }
    @GetMapping
    public List<Department> getAll(){
        return departmentService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Department> getOne(@PathVariable Integer id){
        Department department = departmentService.getOne(id);
        return ResponseEntity.status(department!=null ? HttpStatus.OK:HttpStatus.NOT_FOUND).body(department);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        ApiResponse response = departmentService.deleteDepartment(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
