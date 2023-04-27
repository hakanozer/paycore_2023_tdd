package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.services.CustomerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JPATest {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    CustomerRepository customerRepository;


    @Test
    @Order(1)
    public void customerSave() {
        Customer customer = new Customer();
        customer.setName("Ahmet Bilsin");
        customer.setEmail("ahmet@mail.com");
        customerRepository.save(customer);
        Assertions.assertEquals( customer.getCid(), 1, "Customer Insert Fail" );
    }

    @Test
    @Order(2)
    @Sql(scripts = "classpath:customerSample.sql")
    public void customerFind() {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase("ahmet@mail.com");
        Assertions.assertTrue(optionalCustomer.isPresent(), "ahmet@mail.com not found");
    }

    @Test
    @Order(3)
    @Sql(scripts = "classpath:customerSample.sql")
    public void customers() {
        List<Customer> list = customerRepository.findAll();
        System.out.println("Size : " + list.size());
        Assertions.assertTrue( list.size() > 2, "Size Fail");
    }


    @Order(4)
    @ParameterizedTest
    @ValueSource(strings = {"ali@mail.com", "ahmet@mail.com", "zehra@mail.com"})
    @Sql(scripts = "classpath:customerSample.sql")
    public void params( String item ) {
        System.out.println( "apiKey: " + apiKey );
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(item);
        Assertions.assertTrue(optionalCustomer.isPresent(), item+" not found");
    }

    @Order(5)
    @ParameterizedTest
    @CsvSource(value = { "Ali, a_100", "Zehra, a_200" })
    public void allParams( String param1, String param2  ) {
        System.out.println(param1 + " - " + param2);
    }

    @Test
    @Order(6)
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void timeOut() {
        try {
            Thread.sleep(1100);
            System.out.println("This Line Call");
        }catch (Exception ex) {

        }
    }

    @Order(7)
    @RepeatedTest(10)
    public void repeat() {
        Runnable rn = () -> {
            try {
                Thread.sleep(10);
                String name = Thread.currentThread().getName();
                System.out.println("This Line Call - " + name);
                Assertions.assertTrue(true, "Thread in Text Fail: - " + name);
            }catch (Exception ex) {
                System.err.println(ex);
            }
        };
        new Thread(rn).start();

    }


}
