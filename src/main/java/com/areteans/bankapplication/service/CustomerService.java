package com.areteans.bankapplication.service;

import com.areteans.bankapplication.models.*;
import com.areteans.bankapplication.repository.AccountRepository;
import com.areteans.bankapplication.repository.CustomerRepository;
import com.areteans.bankapplication.repository.DetailsRepository;
import com.areteans.bankapplication.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final DetailsRepository detailsRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public Float interest(Float rate, Long principle, Integer duration) {
        Float interest = (principle*rate*duration)/100;
        return interest;
    }

    public Customer create(Customer customer) {
        Details details= detailsRepository.create(customer.getDetails());
        Account account= accountRepository.create(customer.getAccount());
        customerRepository.create(customer, details.getDetailid(),account.getAccid());
        return customer;
    }

    @Transactional
    public Map<String, Object> createFD(Map<String,Object> customer) {
        Long detailid = detailsRepository.createFD( (Map)customer.get("details"));
        Float interest = interest(7.0f, Long.valueOf((String)((Map<?, ?>) customer.get("account")).get("principle")), (Integer)((Map<?, ?>) customer.get("account")).get("duration"));
        Long accid = accountRepository.createFD((Map)customer.get("account"), interest);
        Long cid = customerRepository.create(detailid,accid);
        customer.put("cid",cid);
        Map<String,Object> map = (Map<String, Object>) customer.get("account");
        map.put("interest", interest);
        customer.put("account",map);
        return  customer;
    }

    public Long getbalance(Long accid) {
        return accountRepository.getbalance(accid);
    }

    public Long deposit(Long amount, Long accid, String date) {
        Long balance = accountRepository.getbalance(accid);
        balance+= amount;
        accountRepository.update(balance, accid);
        Transaction transaction = new Transaction(amount,date,"Deposit");
        transactionRepository.save(transaction,accid);
        return accountRepository.getbalance(accid);
    }

    public Long withdraw(Long amount, Long accid, String date) {
        Long balance = accountRepository.getbalance(accid);
        balance-= amount;
        accountRepository.update(balance, accid);
        Transaction transaction = new Transaction(amount,date,"Withdrawal");
        transactionRepository.save(transaction,accid);
        return accountRepository.getbalance(accid);
    }
}

//        AccountJPA accountJPA = accountRepository.save(customer.getAccountJPA());
//        DetailsJPA detailsJPA = detailsRepository.save(customer.getDetailsJPA());
//        CustomerJPA customerJPA = new CustomerJPA();
//        customerJPA.setDetailid(detailsJPA.getDetailid());
//        customerJPA.setAccid(accountJPA.getAccid());
//        customerJPA = customerRepository.save(customerJPA);
//        customer.setCid(customerJPA.getCid());
//        return customer;

//        Map<String, Object> map1 = jdbcTemplate.queryForMap("insert into details(name,address,contact) values(?,?,?) RETURNING detailid",
//                customer.getDetail().getName(),
//                customer.getDetail().getAddress(),
//                customer.getDetail().getContact());
//
//        Map<String, Object> map2 = jdbcTemplate.queryForMap("insert into account(balance,accountno,acctype) values(?,?,?) RETURNING accid",
//                0L,
//                customer.getAccount().getAccountno(),
//                customer.getAccount().getAcctype());
//        Map<String,Object> map3= jdbcTemplate.queryForMap("insert into customer(detailid,accid) values(?,?) RETURNING cid",
//                map1.get("detailid"),
//                map2.get("accid"));
//                customer.setCid((Long)map3.get("cid"));
//        return customer;
//    }

//    public Long updateBalance(Long balance, String accountno) {
//        int update = accountRepository.updatebalance(balance,accountno);
//        Map<String, Object> map = jdbcTemplate.queryForMap("select balance from account where accountno=?", accountno);
//        return (Long) map.get("balance");
//
//        jdbcTemplate.update("update account set balance=? where accountno=?",
//                balance, accountno);
//        Map<String, Object> map = jdbcTemplate.queryForMap("select balance from account where accountno=?", accountno);
//        return (Long) map.get("balance");
//    }
//
//    public CustomerCopy readdetails(Long cid) throws CustomException {
//        Optional<CustomerJPA>customer =customerRepository.findById(cid);
//        if(customer.isPresent()) {
//            Long detailid = customer.get().getDetailid();
//            Long accid = customer.get().getAccid();
//            Optional<DetailsJPA> detailsJPA = detailsRepository.findById(detailid);
//            Optional<AccountJPA> accountJPA = accountRepository.findById(accid);
//            CustomerCopy customerCopy = new CustomerCopy(detailsJPA.get(),accountJPA.get(),cid);
//            return customerCopy;
//        }
//        else {
//            throw new CustomException("Customer not found");
//        }
//
//        Map<String,Object> map= jdbcTemplate.queryForMap("select * from customer where cid=?", cid);
//        Long detailid= (Long) map.get("detailid");
//        Long accid= (Long) map.get("accid");
//        Details obj= jdbcTemplate.queryForObject("select * from details where detailid=" + detailid, new RowMapper<Details>() {
//            @Override
//            public Details mapRow(ResultSet resultSet, int i) throws SQLException {
//                return new Details(resultSet.getString("name"), resultSet.getString("address"), resultSet.getLong("contact"));
//
//            }
//        }
//        );
//        Account obj1= jdbcTemplate.queryForObject("select * from account where accid=" + accid, new RowMapper<Account>() {
//            @Override
//            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
//                return new Account(resultSet.getLong("balance"), resultSet.getString("accountno"), resultSet.getString("acctype"));
//            }
//        });
//        return new Customer(obj,obj1,cid);
//    }
//
//    public void delete(Long cid) {
//        Map<String,Object> map = jdbcTemplate.queryForMap("select detailid,accid from customer where cid=?" , cid);
//        jdbcTemplate.update("delete from customer where detailid=?",map.get("detailid"));
//        jdbcTemplate.update("delete from account where accid=?", map.get("accid"));
//        jdbcTemplate.update("delete from details where detailid=?", map.get("detailid"));
//    }
