package com.areteans.bankapplication.service;

import com.areteans.bankapplication.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final JdbcTemplate jdbcTemplate;
    public Customer save(Customer customer) {
        customer.getDetail();
        customer.getAccount();
        Map<String, Object> map1= jdbcTemplate.queryForMap("insert into details(name,address,contact) values(?,?,?) RETURNING detailid",
                customer.getDetail().getName(),
                customer.getDetail().getAddress(),
                customer.getDetail().getContact());
        Map<String, Object> map2= jdbcTemplate.queryForMap("insert into account(balance,accountno,acctype) values(?,?,?) RETURNING accid",
                0L,
                customer.getAccount().getAccountno(),
                customer.getAccount().getAcctype());
        jdbcTemplate.update("insert into customer(detailid,accid) values(?,?)",
                map1.get("detailid"),
                map2.get("accid"));
        return customer;
    }
}
