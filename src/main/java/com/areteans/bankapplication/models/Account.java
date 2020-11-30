package com.areteans.bankapplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Account {
    private long balance;
    private String accountno;
    private String acctype;
    private Long accid;

    public Account(Long balance, String accountno, String accType) {
        this.balance= balance;
        this.accountno= accountno;
        this.acctype= accType;
    }
}
