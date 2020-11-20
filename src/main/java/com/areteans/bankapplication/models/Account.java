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

    public Account(Long balance, String accountno, String accType) {
        this.balance= balance;
        this.setAccountno(accountno);
        this.setAcctype(accType);
    }

}
