package uz.demo.spring_binding_tables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.demo.spring_binding_tables.model.Address;
import uz.demo.spring_binding_tables.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/address")
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @PostMapping("/address")
    public String addAddress(@RequestBody Address address) {
        addressRepository.save(address);
        return "Address saved";
    }

    @DeleteMapping("/address-delete/{id}")
    public String deleteAddress(@PathVariable Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            addressRepository.deleteById(id);
            return "Address deleted";
        }
        return "Address not found";
    }

    @PutMapping("/address-editing/{id}")
    public String updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            address.setId(id);
            addressRepository.save(address);
            return "Address updated";
        }
        return "Address not found";
    }


}

