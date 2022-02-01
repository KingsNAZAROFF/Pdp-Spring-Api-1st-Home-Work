package uz.pdp.pdpspringapi1sthomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspringapi1sthomework.entity.Worker;
import uz.pdp.pdpspringapi1sthomework.payload.ApiResponse;
import uz.pdp.pdpspringapi1sthomework.payload.WorkerDto;
import uz.pdp.pdpspringapi1sthomework.service.WorkerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@Valid @RequestBody WorkerDto workerDto){
        ApiResponse response = workerService.addWorker(workerDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWorker(@PathVariable Integer id,@Valid @RequestBody WorkerDto workerDto){
        ApiResponse response = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping
    public Page<Worker> getAll(@RequestParam int page){
        return workerService.getAll(page);
    }
    @GetMapping("/{byDepartmentId}")
    public Page<Worker> getAllByDepartmentId(@PathVariable Integer byDepartmentId,int page){
        return workerService.getAllByDepartmentId(byDepartmentId,page);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getOne(@PathVariable Integer id){
        Worker worker = workerService.getOne(id);
        return ResponseEntity.status(worker!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(worker);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
        ApiResponse response = workerService.deleteWorker(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }

}
