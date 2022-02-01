package uz.pdp.pdpspringapi1sthomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspringapi1sthomework.entity.Address;
import uz.pdp.pdpspringapi1sthomework.payload.ApiResponse;
import uz.pdp.pdpspringapi1sthomework.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value ="/api/address")
public class AddressController {

    @Autowired
    AddressService service;


    @GetMapping
    public ResponseEntity<Page<Address>> getAll(@RequestParam int page){

        Page<Address> addresses = service.getAddresses(page);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getOne(@PathVariable Integer id){
        Address one = service.getOne(id);
        return ResponseEntity.status(one != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(one);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addAddress(@Valid  @RequestBody Address address){
        ApiResponse response = service.addAddress(address);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_ACCEPTABLE).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAddress(@PathVariable Integer id,@Valid @RequestBody Address address){
        ApiResponse response = service.editAddress(id, address);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = service.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
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
