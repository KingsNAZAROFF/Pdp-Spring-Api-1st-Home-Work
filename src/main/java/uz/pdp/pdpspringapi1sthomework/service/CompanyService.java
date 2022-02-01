package uz.pdp.pdpspringapi1sthomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspringapi1sthomework.entity.Address;
import uz.pdp.pdpspringapi1sthomework.entity.Company;
import uz.pdp.pdpspringapi1sthomework.payload.ApiResponse;
import uz.pdp.pdpspringapi1sthomework.payload.CompanyDto;
import uz.pdp.pdpspringapi1sthomework.repository.AddressRepository;
import uz.pdp.pdpspringapi1sthomework.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addCompany(CompanyDto companyDto){
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address saved = addressRepository.save(address);
        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(saved);
        companyRepository.save(company);
        return new ApiResponse("Company DB ga saqlandi",true);

    }
    public ApiResponse editCompany(Integer id, CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()){
            return new ApiResponse("Bunday Company mavjud emas",false);
        }
        Company editingCompany = optionalCompany.get();
        Address editingAddress = editingCompany.getAddress();
        editingAddress.setStreet(companyDto.getStreet());
        editingAddress.setHomeNumber(companyDto.getHomeNumber());
        Address saved = addressRepository.save(editingAddress);
        editingCompany.setCorpName(companyDto.getCorpName());
        editingCompany.setDirectorName(companyDto.getDirectorName());
        editingCompany.setAddress(saved);
        companyRepository.save(editingCompany);
        return new ApiResponse("Company ma'lumotlari o'zgartirildi",true);

    }
    public List<Company> getAll(){
        return companyRepository.findAll();
    }
    public Company getOne(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }
    public ApiResponse deleteCompany(Integer id){
        Optional<Company> byId = companyRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse("Bunday Company mavjud emas",false);
        }
        companyRepository.deleteById(id);
        addressRepository.deleteById(byId.get().getAddress().getId());
        return new ApiResponse("Company Dbdan o'chirildi",true);
    }



}
