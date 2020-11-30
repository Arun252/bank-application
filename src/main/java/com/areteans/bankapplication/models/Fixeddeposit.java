package com.areteans.bankapplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Fixeddeposit extends Account{
    private Integer duration;
    private final float rate = 7;
    private Float interest;
    private Integer principle;
    public Fixeddeposit(Long balance, String accno, Integer t, Integer principle, String acctype) {
        super(balance,accno,acctype);
        this.duration=t;
        this.principle= principle;
    }
}
