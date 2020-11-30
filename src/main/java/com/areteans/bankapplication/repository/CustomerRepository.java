package com.areteans.bankapplication.repository;

import com.areteans.bankapplication.models.Account;
import com.areteans.bankapplication.models.Customer;
import com.areteans.bankapplication.models.Details;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final JdbcTemplate jdbcTemplate;
    public Customer create(Customer customer, Long detailid, Long accid) {
        Map<String,Object> map3= jdbcTemplate.queryForMap("insert into customer(detailid,accid) values(?,?) RETURNING cid",
                detailid,
                accid);
                customer.setCid((Long)map3.get("cid"));
        return customer;
    }

    public Long create(Long detailid, Long accid) {
        Map<String,Object> map3= jdbcTemplate.queryForMap("insert into customer(detailid,accid) values(?,?) RETURNING cid",
                detailid,
                accid);
        return (Long) map3.get("cid");

    }
}
