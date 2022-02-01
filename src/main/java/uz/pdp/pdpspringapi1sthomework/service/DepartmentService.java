package uz.pdp.pdpspringapi1sthomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspringapi1sthomework.entity.Company;
import uz.pdp.pdpspringapi1sthomework.entity.Department;
import uz.pdp.pdpspringapi1sthomework.payload.ApiResponse;
import uz.pdp.pdpspringapi1sthomework.payload.CompanyDto;
import uz.pdp.pdpspringapi1sthomework.payload.DepartmentDto;
import uz.pdp.pdpspringapi1sthomework.repository.CompanyRepository;
import uz.pdp.pdpspringapi1sthomework.repository.DepartmentRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;
    public ApiResponse addDepartment(@Valid DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()){
            return new ApiResponse("Bunday company mavjud emas",false);
        }
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Department DB ga saqlandi",true);

    }
    public ApiResponse editDepartment(Integer id,DepartmentDto departmentDto){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()){
            return new ApiResponse("Bunday Department mavjud emas",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()){
            return new ApiResponse("Bunday company mavjud emas",false);
        }
        Department editingDepartment = optionalDepartment.get();
        editingDepartment.setName(departmentDto.getName());
        editingDepartment.setCompany(optionalCompany.get());
        departmentRepository.save(editingDepartment);
        return new ApiResponse("Department ma'lumotlari o'zgartirildi",true);
    }
    public List<Department> getAll(){
        return departmentRepository.findAll();
    }
    public Department getOne(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }
    public ApiResponse deleteDepartment(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()){
            return new ApiResponse("Bunday department mavjud emas",false);
        }
        departmentRepository.deleteById(id);
        return new ApiResponse("Department DB dan o'chirildi",true);
    }
}
