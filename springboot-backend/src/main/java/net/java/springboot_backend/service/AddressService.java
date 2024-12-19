package net.java.springboot_backend.service;

import jakarta.transaction.Transactional;
import net.java.springboot_backend.model.Address;
import net.java.springboot_backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void addAddressToo(Address address) {
        addressRepository.save(address);
    }

}