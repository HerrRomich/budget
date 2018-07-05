package com.hrrm.budget.domain.account.repository.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import com.hrrm.budget.domain.account.AccountGroup;
import com.hrrm.budget.domain.account.repository.AccountGroupRepository;

@Component(service = AccountGroupRepository.class, scope = ServiceScope.SINGLETON)
public class AccountGroupRepositoryImpl extends AccountBasicJpaRepository<AccountGroup> implements AccountGroupRepository {

	@Override
	protected Class<AccountGroup> getEntityClass() {
		return AccountGroup.class;
	}

}
