package com.hrrm.budget.domain.account;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@NamedQueries(value = { @NamedQuery(name = "findAllAccounts", query = "select a from Account a") })
public class Account extends AccountBaseEntity {

    @Column(name = "name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "tag")
    @CollectionTable(name = "account_tag", joinColumns = { @JoinColumn(name = "account_id") })
    private Set<String> tags;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
