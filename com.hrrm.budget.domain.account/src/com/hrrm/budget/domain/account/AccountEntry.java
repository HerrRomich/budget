package com.hrrm.budget.domain.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "account_entry")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entry_type")
public abstract class AccountEntry extends AccountBaseEntity {

    @Column(name = "account_id")
    private Integer accountId;
    
    @Column(name = "entry_date")
    private LocalDateTime entryDate;
    
    @Column(name = "transact_date")
    private LocalDateTime transactionDate;
    
    @Column(name = "budget_period")
    private LocalDate budgetPeriod;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "comment")
    private String comment;
    
}
