package uz.pdp.pdpspringapi1sthomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspringapi1sthomework.entity.Address;
import uz.pdp.pdpspringapi1sthomework.entity.Department;
import uz.pdp.pdpspringapi1sthomework.entity.Worker;
import uz.pdp.pdpspringapi1sthomework.payload.ApiResponse;
import uz.pdp.pdpspringapi1sthomework.payload.WorkerDto;
import uz.pdp.pdpspringapi1sthomework.repository.AddressRepository;
import uz.pdp.pdpspringapi1sthomework.repository.DepartmentRepository;
import uz.pdp.pdpspringapi1sthomework.repository.WorkerRepository;

import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponse addWorker(WorkerDto workerDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()){
            return new ApiResponse("Bunday department mavjud emas",false);
        }
        boolean exists = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (exists){
            return new ApiResponse("Ushbu telefon raqam ro'yxatdan o'tkazilgan",false);
        }
        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        Worker worker = new Worker();
        worker.setName(worker.getName());
        worker.setPhoneNumber(worker.getPhoneNumber());
        worker.setAddress(savedAddress);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Worker DB ga saqlandi",true);
    }
    public ApiResponse editWorker(Integer id,WorkerDto workerDto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()){
            return new ApiResponse("Bunday worker mavjud emas",false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());

        if (optionalDepartment.isEmpty()){
            return new ApiResponse("Bunday department mavjud emas",false);
        }
        boolean exists = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (exists){
            return new ApiResponse("Ushbu telefon raqam ro'yxatdan o'tkazilgan",false);
        }
        Address editingAddress = optionalWorker.get().getAddress();
        editingAddress.setStreet(workerDto.getStreet());
        editingAddress.setHomeNumber(workerDto.getHomeNumber());
        Address saved = addressRepository.save(editingAddress);
        Worker editingWorker = optionalWorker.get();
        editingWorker.setName(workerDto.getName());
        editingWorker.setPhoneNumber(workerDto.getPhoneNumber());
        editingWorker.setDepartment(optionalDepartment.get());
        editingWorker.setAddress(saved);
        workerRepository.save(editingWorker);
        return new ApiResponse("Worker ma'lumotlari o'zgartirildi",true);

    }
    public Page<Worker> getAll(int page){
        Pageable pageable = PageRequest.of(page,15);
        return workerRepository.findAll(pageable);
    }
    public Worker getOne(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }
    public Page<Worker> getAllByDepartmentId(Integer id, int page){
        Pageable pageable = PageRequest.of(page,15);
        return workerRepository.findAllByDepartmentId(id,pageable);
    }
    public ApiResponse deleteWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()){
            return new ApiResponse("Bunday worker mavjud emas",false);
        }
        workerRepository.deleteById(id);
        return new ApiResponse("Worker DB dan o'chirildi",true);
    }
}
