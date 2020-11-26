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
    private Details details;
    private Account account;
    private Long cid;
    public Customer(String name, String address, Long contact, String accountno,Integer duration,Integer principle, String accType) {
        this.details= new Details(name, address, contact);
        if(AccType.FIXEDDEPOSIT.toString().equals(accType)) {
            this.account= new Fixeddeposit(0L,accountno,duration,principle,accType);
        }
        else if(AccType.SAVINGS.toString().equals(accType)) {
            this.account= new Account(0L,accountno,accType);
        }
    }
}
