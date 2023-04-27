package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    final CustomerService customerService;

    @GetMapping("/customer/list")
    public List<Customer> list(){
        return customerService.list();
    }

    @PostMapping("/customer/save")
    public ResponseEntity save(@RequestBody Customer customer) {
        Customer cx = customerService.save(customer);
        if ( cx != null ) {
            // return new ResponseEntity(cx, HttpStatus.OK);
        }else {
            // return new ResponseEntity(cx, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cx, HttpStatus.OK);
    }


}
