package com.areteans.bankapplication.controller;

import com.areteans.bankapplication.models.Customer;
import com.areteans.bankapplication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(path ="create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PostMapping(path ="fixeddeposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> create(@RequestBody Map<String,Object> customer) {
        return customerService.create(customer);
    }

    @PostMapping(path = "balance" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long getbalance(@RequestBody Map<String, Object> map) {
        return customerService.getbalance(Long.valueOf((String) map.get("accid")));
        // ((Long) map.get("accid))
    }

    @PostMapping(path = "deposit" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long deposit(@RequestBody Map<String, Object> map) {
        return customerService.deposit(Long.valueOf((String)map.get("amount")) ,Long.valueOf((String)map.get("accid")));
    }
//
//    @PutMapping(path= "update", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Long update(@RequestBody Map<String, Object> map ) {
//        return customerService.updateBalance(Long.valueOf((String) map.get("balance")), (String) map.get("accountno"));
//    }
//
//    @GetMapping(path= "read")
//    public CustomerCopy read(@RequestParam("cid") Long cid ) throws CustomException {
//        return customerService.readdetails(cid);
//    }
//
//    @PostMapping(path= "delete", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void delete(@RequestBody Map<String,String> delete) {
//        customerService.delete(Long.valueOf(delete.get("cid")));
//    }
//
//    @PostMapping(path ="create", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public CustomerCopy create(@RequestBody CustomerCopy customer) {
//        return customerService.create(customer);
//    }
}
