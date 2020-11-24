package com.areteans.bankapplication.controller;

import com.areteans.bankapplication.models.Customer;
import com.areteans.bankapplication.models.CustomerCopy;
import com.areteans.bankapplication.service.CustomerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="customer")
public class CustomerController {
    private final CustomerService customerService;

//    @PostMapping(path ="create", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Customer create(@RequestBody Customer customer) {
//        return customerService.create(customer);
//    }
//
//    @PostMapping(path= "update", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Long update(@RequestBody Map<String,String> update) {
//        return customerService.updateBalance(update.get("accountno"), Long.valueOf(update.get("balance")));
//    }
//
    @GetMapping(path= "read")
    public CustomerCopy read(@RequestParam("cid") Long cid ) {
        return customerService.readdetails(cid);
    }
//
//    @PostMapping(path= "delete", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void delete(@RequestBody Map<String,String> delete) {
//        customerService.delete(Long.valueOf(delete.get("cid")));
//    }

    @PostMapping(path ="create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerCopy create(@RequestBody CustomerCopy customer) {
        return customerService.create(customer);
    }
}
