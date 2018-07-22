package com.hrrm.budget.domain.account.repository.impl;

import javax.persistence.EntityManager;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.transaction.control.TransactionControl;

import com.hrrm.budget.domain.account.AccountBaseEntity;
import com.hrrm.budget.domain.account.repository.AccountBaseEntityRepository;
import com.hrrm.infrastructure.persistence.impl.JpaRepositoryImpl;

public abstract class AccountBaseJpaRepository<Entity extends AccountBaseEntity>
    extends JpaRepositoryImpl<Entity, Integer> implements AccountBaseEntityRepository<Entity> {

  @Reference
  protected TransactionControl txControl;

  @Reference(target = "(name=accounts)")
  protected EntityManager entityManager;

  @Activate
  public void activate() {
    entityManager.toString();
  }

  @Override
  protected TransactionControl getTxControl() {
    return txControl;
  }

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

}
