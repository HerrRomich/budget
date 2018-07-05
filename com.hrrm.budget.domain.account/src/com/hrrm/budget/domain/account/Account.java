package com.hrrm.budget.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@NamedQueries(value = { @NamedQuery(name = "findAllAccounts", query = "select a from Account a") })
public class Account extends AccountBaseEntity {

    @Column(name = "name")
    private String name;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
