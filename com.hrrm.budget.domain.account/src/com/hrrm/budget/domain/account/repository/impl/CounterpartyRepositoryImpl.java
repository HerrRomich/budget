package com.hrrm.budget.domain.account.repository.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import com.hrrm.budget.domain.account.Counterparty;
import com.hrrm.budget.domain.account.repository.CounterpartyRepository;

@Component(service = CounterpartyRepository.class, scope = ServiceScope.SINGLETON)
public class CounterpartyRepositoryImpl extends AccountBaseJpaRepository<Counterparty> implements CounterpartyRepository {

    @Override
    protected Class<Counterparty> getEntityClass() {
	return Counterparty.class;
    }

}
