package com.piramal.lms.simple.model;

import lombok.Data;

@Data
public class ActiveLoan {

    private String loanNumber;
    private String productId;
    private double sanctionAmount;
    private double dueAmount;
}
