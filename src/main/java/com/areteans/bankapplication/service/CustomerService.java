package com.areteans.bankapplication.service;

import com.areteans.bankapplication.models.Account;
import com.areteans.bankapplication.models.Customer;
import com.areteans.bankapplication.models.Details;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final JdbcTemplate jdbcTemplate;

    public Customer create(Customer customer) {
        customer.getDetail();
        customer.getAccount();
        Map<String, Object> map1 = jdbcTemplate.queryForMap("insert into details(name,address,contact) values(?,?,?) RETURNING detailid",
                customer.getDetail().getName(),
                customer.getDetail().getAddress(),
                customer.getDetail().getContact());

        Map<String, Object> map2 = jdbcTemplate.queryForMap("insert into account(balance,accountno,acctype) values(?,?,?) RETURNING accid",
                0L,
                customer.getAccount().getAccountno(),
                customer.getAccount().getAcctype());
        Map<String,Object> map3= jdbcTemplate.queryForMap("insert into customer(detailid,accid) values(?,?) RETURNING cid",
                map1.get("detailid"),
                map2.get("accid"));
                customer.setCid((Long)map3.get("cid"));
        return customer;
    }

    public Long updateBalance(String accountno , Long balance) {
        jdbcTemplate.update("update account set balance=? where accountno=?",
                balance, accountno);
        Map<String, Object> map = jdbcTemplate.queryForMap("select balance from account where accountno=?", accountno);
        return (Long) map.get("balance");
    }

    public Customer readdetails(Long cid) {
        Map<String,Object> map= jdbcTemplate.queryForMap("select * from customer where cid=?", cid);
        Long detailid= (Long) map.get("detailid");
        Long accid= (Long) map.get("accid");
        Details obj= jdbcTemplate.queryForObject("select * from details where detailid=" + detailid, new RowMapper<Details>() {
            @Override
            public Details mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Details(resultSet.getString("name"), resultSet.getString("address"), resultSet.getLong("contact"));

            }
        }
        );
        Account obj1= jdbcTemplate.queryForObject("select * from account where accid=" + accid, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Account(resultSet.getLong("balance"), resultSet.getString("accountno"), resultSet.getString("acctype"));
            }
        });
        return new Customer(obj,obj1,cid);
    }

    public void delete(Long cid) {
        Map<String,Object> map = jdbcTemplate.queryForMap("select detailid,accid from customer where cid=?" , cid);
        jdbcTemplate.update("delete from customer where detailid=?",map.get("detailid"));
        jdbcTemplate.update("delete from account where accid=?", map.get("accid"));
        jdbcTemplate.update("delete from details where detailid=?", map.get("detailid"));
    }
}