package com.areteans.bankapplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Customer {
    private Details detail;
    private Account account;
    public Customer(String name, String address, Long contact, String accountno,int time,int principle, String accType) {
        this.detail= new Details(name, address, contact);
        if(AccType.FIXEDDEPOSIT.toString().equals(accType)) {
            this.account= new FixedDp(0L,accountno,accType,time,principle);
        }
        else if(AccType.SAVINGS.toString().equals(accType)) {
            this.account= new Account(0L,accountno,accType);
        }
    }
}
