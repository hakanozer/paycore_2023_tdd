package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    public Customer save( Customer customer ) {
        try {
            customerRepository.save(customer);
            return customer;
        }catch (Exception ex) {
            return null;
        }
    }

    public List<Customer> list() {
        return customerRepository.findAll();
    }

}
