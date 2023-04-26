package com.works;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Customer;
import com.works.props.JWTUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@SpringBootTest
public class CustomerRestController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void customerSave() {
        //Customer customer = new Customer();
        //customer.setEmail("test_"+ UUID.randomUUID()  +"@mail.com");
        //customer.setName("Ali Bilmem");

        JWTUser customer = new JWTUser();
        customer.setPassword("kminchelle");
        customer.setUsername("0lelplR");

        String url = "https://dummyjson.com/auth/login";
        String stData = "";
        try {
            stData = objectMapper.writeValueAsString(customer);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(stData, headers );
            Object response = restTemplate.postForObject(url,httpEntity, Object.class);
            //Assertions.assertEquals( response, "Customer Save Fail");
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }

}
