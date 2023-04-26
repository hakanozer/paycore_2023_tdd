package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void listTest() {
        List<Customer> list = customerRepository.findAll();
        Assertions.assertTrue(list.size() > 3, "3 Size Problem");
    }

}
