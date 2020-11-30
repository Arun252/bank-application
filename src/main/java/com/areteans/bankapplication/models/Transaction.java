package com.areteans.bankapplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    private Long transid;
    private Long amount;
    private String date;
    private String transtype;

    public Transaction(Long amount, String date, String transtype) {
        this.amount = amount;
        this.date = date;
        this.transtype = transtype;
    }
}
