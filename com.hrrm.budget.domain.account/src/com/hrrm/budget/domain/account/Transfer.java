package com.hrrm.budget.domain.account;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TRANSFER")
public class Transfer extends AccountEntry {
    
    @Column(name = "transfer_contrary_id")
    private Integer contraryId; 

}
