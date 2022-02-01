package uz.pdp.pdpspringapi1sthomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspringapi1sthomework.entity.Address;
import uz.pdp.pdpspringapi1sthomework.payload.ApiResponse;
import uz.pdp.pdpspringapi1sthomework.repository.AddressRepository;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addAddress(Address address){
        addressRepository.save(address);
        return new ApiResponse("Address saqlandi",true);
    }
    public Page<Address> getAddresses(int page){
        Pageable pageable = PageRequest.of(page,10);
        return addressRepository.findAll(pageable);
    }
    public Address getOne(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }
    public ApiResponse editAddress(Integer id,Address address){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            return new ApiResponse("Bunday address mavjud emas",false);
        }
        Address editingAddress = optionalAddress.get();
        editingAddress.setHomeNumber(address.getHomeNumber());
        editingAddress.setStreet(address.getStreet());
        addressRepository.save(editingAddress);
        return new ApiResponse("Address ma'lumotlari o'zgartirildi",true);
    }
    public ApiResponse deleteAddress(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            return new ApiResponse("Bunday address mavjud emas",false);
        }
        addressRepository.deleteById(id);
        return new ApiResponse("Address Dbdan o'chirildi",true);
    }
}
