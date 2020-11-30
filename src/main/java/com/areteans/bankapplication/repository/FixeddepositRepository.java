package com.areteans.bankapplication.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FixeddepositRepository {
    private final JdbcTemplate jdbcTemplate;

}
