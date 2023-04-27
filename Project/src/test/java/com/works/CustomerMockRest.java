package com.works;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.restcontrollers.CustomerRestController;
import com.works.services.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@WebMvcTest(CustomerRestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerMockRest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;


    @Test
    @Order(3)
    public void customerSave() {
        String url = "/customer/save";
        Customer customer = new Customer();
        customer.setEmail("serkan@mail.com");
        customer.setName("Kemal Bil");
        String customerSt = "";
        try {
            customerSt = objectMapper.writeValueAsString(customer);
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(customerSt)
            ).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String st = mvcResult.getRequest().getContentAsString();
            System.out.println( st );
            Assertions.assertEquals(200, status, "/customer/save Fail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void customerServiceSave() {
        Customer customer = new Customer();
        customer.setEmail("serkanx1@mail.com");
        customer.setName("Kemal Bil");
        customerRepository.save(customer);
        System.out.println(customer);
    }

    @Test
    @Order(2)
    public void allCustomer() {
        // customerServiceSave();
        List<Customer> ls = customerRepository.findAll();
        System.out.println("Lss : " + ls );
    }


}
