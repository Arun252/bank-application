package com.areteans.bankapplication.repository;


import com.areteans.bankapplication.models.Details;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DetailsRepository {
    private final JdbcTemplate jdbcTemplate;
    public Details create(Details details) {
        Map<String, Object> map1 = jdbcTemplate.queryForMap("insert into details(name,address,contact) values(?,?,?) RETURNING detailid",
                details.getName(),
                details.getAddress(),
                details.getContact());
        details.setDetailid((Long) map1.get("detailid"));
        return details;
    }

    public Long create(Map<String,Object> details) {
        Map<String, Object> map1 = jdbcTemplate.queryForMap("insert into details(name,address,contact) values(?,?,?) RETURNING detailid",
                ((String)details.get("name")),
                (String)details.get("address"),
                Long.valueOf((String)details.get("contact")));
        return (Long) map1.get("detailid");

    }
}
