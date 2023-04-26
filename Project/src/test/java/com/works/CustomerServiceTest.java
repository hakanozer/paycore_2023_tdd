package com.works;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.UUID;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    String uui;

    @Test
    public void customerSave() {
        Customer customer = new Customer();
        customer.setEmail("test_"+ uui +"@mail.com");
        customer.setName("Ali Bilmem");
        Customer cx = customerService.save(customer);

        Assertions.assertAll(
                () ->  Assertions.assertNotNull(cx,"Insert Fail" ),
                () -> Assertions.assertTrue(cx.getCid() > 0, "ID Fail")
        );
    }

}
