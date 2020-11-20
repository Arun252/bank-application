package com.areteans.bankapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Details detail;
    private Account account;
    private Long cid;
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
