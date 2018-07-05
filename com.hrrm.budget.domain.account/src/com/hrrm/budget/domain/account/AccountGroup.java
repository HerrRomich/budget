package com.hrrm.budget.domain.account;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account_group")
@NamedQueries({@NamedQuery(name = "findAllAccountGroups", query = "select ag from AccountGroup ag")})
public class AccountGroup extends AccountBaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "group_id")
	private Set<Account> accounts;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(Set<Account> accounts) {
    this.accounts = accounts;
  }

}
