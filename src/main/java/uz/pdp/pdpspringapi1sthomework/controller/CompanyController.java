package uz.pdp.pdpspringapi1sthomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspringapi1sthomework.entity.Company;
import uz.pdp.pdpspringapi1sthomework.payload.ApiResponse;
import uz.pdp.pdpspringapi1sthomework.payload.CompanyDto;
import uz.pdp.pdpspringapi1sthomework.service.CompanyService;

import javax.validation.Valid;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;


    @PostMapping
    public ResponseEntity<ApiResponse> addCompany(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK : HttpStatus.METHOD_NOT_ALLOWED).body(apiResponse);
    }
    @GetMapping
    public ResponseEntity<List<Company>> getAll(){
        return ResponseEntity.ok(companyService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getOne(@PathVariable Integer id){
        return ResponseEntity.status(companyService.getOne(id)!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(companyService.getOne(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCompany(@PathVariable Integer id,@Valid @RequestBody CompanyDto companyDto){
        ApiResponse response = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id){
        ApiResponse response = companyService.deleteCompany(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }


}
