package com.areteans.bankapplication.controller;

import com.areteans.bankapplication.models.Customer;
import com.areteans.bankapplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(path ="create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer create(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
