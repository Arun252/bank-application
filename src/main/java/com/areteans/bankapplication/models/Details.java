package com.areteans.bankapplication.models;

import lombok.*;
import org.springframework.jdbc.core.JdbcTemplate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    private String name;
    private String address;
    private Long contact;
    private Long detailid;

    public Details(String name, String address, Long contact) {
        this.setName(name);
        this.setAddress(address);
        this.setContact(contact);
    }
}