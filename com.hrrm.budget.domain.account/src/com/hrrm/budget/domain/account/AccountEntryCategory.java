package com.hrrm.budget.domain.account;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "entry_category")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AccountEntryCategory extends TextLookupEntity {

    @Column(name = "parent_id")
    private Integer parentId;

}
