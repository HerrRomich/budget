package com.hrrm.budget.domain.account;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BasketItem {

    @Column(name = "category_id")
    private Integer categoryId;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "comment")
    private String comment;
    
}
