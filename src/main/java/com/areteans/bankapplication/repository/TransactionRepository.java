package com.areteans.bankapplication.repository;

import com.areteans.bankapplication.models.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
    public void save(Transaction transaction , Long accid) {
        jdbcTemplate.update("insert into transaction (amount,date,transtype,accid) values(?,?,?,?)",
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getTranstype(),
                accid);
    }
}
