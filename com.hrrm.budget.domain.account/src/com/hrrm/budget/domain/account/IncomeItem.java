package com.hrrm.budget.domain.account;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("INCOME")
public class IncomeItem extends AccountEntryCategory {

    @OneToMany(fetch = FetchType.EAGER)
    List<IncomeItem> subcategories;
    
}
