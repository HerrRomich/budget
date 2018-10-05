package com.hrrm.budget.domain.account.repository.impl;

import javax.persistence.EntityManager;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.transaction.control.TransactionControl;

import com.hrrm.budget.domain.account.AccountBaseEntity;
import com.hrrm.budget.domain.account.repository.AccountBaseEntityRepository;
import com.hrrm.infrastructure.persistence.impl.JpaRepositoryImpl;

public abstract class AccountBaseJpaRepository<T extends AccountBaseEntity>
    extends JpaRepositoryImpl<T, Integer> implements AccountBaseEntityRepository<T> {

  @Reference
  protected TransactionControl txControl;

  @Reference(target = "(name=accounts)")
  protected EntityManager entityManager;

  @Override
  protected TransactionControl getTxControl() {
    return txControl;
  }

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

}
