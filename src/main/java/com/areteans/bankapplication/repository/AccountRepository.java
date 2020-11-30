package com.areteans.bankapplication.repository;

import com.areteans.bankapplication.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
    private final JdbcTemplate jdbcTemplate;
    public Account create(Account account) {
        Map<String, Object> map2 = jdbcTemplate.queryForMap("insert into account(balance,accountno,acctype) values(?,?,?) RETURNING accid",
               0L,
                account.getAccountno(),
                account.getAcctype());
        account.setAccid((Long) map2.get("accid"));
        return account;
    }

    public Long createFD(Map<String, Object> account, Float interest) {
        Map<String, Object> map2 = jdbcTemplate.queryForMap("insert into account(balance,accountno,acctype) values(?,?,?) RETURNING accid",
                0L,
                (String)account.get("accountno"),
                (String)account.get("acctype"));

        jdbcTemplate.update("insert into fixeddeposit(duration,rate,principle,interest,accid) values(?,?,?,?,?)",
                (Integer)account.get("duration"),
                7.0f,
                Long.valueOf((String)account.get("principle")),
                interest,
                (Long)map2.get("accid"));
        return ((Long) map2.get("accid"));
    }

    public Long getbalance(Long accid) {
         Map<String,Object> map =  jdbcTemplate.queryForMap("select balance from account where accid=?",accid);
         return (Long) map.get("balance");
    }

    public void update(Long amount , Long accid) {
        jdbcTemplate.update("update account set balance=? where accid=?" , amount, accid);
    }
}
