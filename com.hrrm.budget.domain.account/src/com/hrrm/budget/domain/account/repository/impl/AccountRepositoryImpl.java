package com.hrrm.budget.domain.account.repository.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import com.hrrm.budget.domain.account.Account;
import com.hrrm.budget.domain.account.repository.AccountRepository;

@Component(service = AccountRepository.class, scope = ServiceScope.SINGLETON)
public class AccountRepositoryImpl extends AccountBasicJpaRepository<Account> implements AccountRepository {

	@Override
	protected Class<Account> getEntityClass() {
		return Account.class;
	}

}
