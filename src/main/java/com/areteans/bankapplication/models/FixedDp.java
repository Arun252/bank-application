package com.areteans.bankapplication.models;

import lombok.Getter;

@Getter
public class FixedDp extends Account {
    private int time;
    private final float rate = 7;
    private double interest;
    private int principle;
    private String accType;
        public FixedDp(Long balance, String accountno,String accType, int time, int principle) {
        super(balance,accountno,accType);
        this.time = time;
        this.principle = principle;
    }
}