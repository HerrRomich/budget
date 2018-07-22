package com.hrrm.budget.domain.account;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SIMPLE")
public class SimpleEntry extends AccountEntry {

    @Column(name = "category_id")
    private Integer categoryId;
    
    @Column(name = "countpart_id")
    private Integer counterpartyId;
    
}
