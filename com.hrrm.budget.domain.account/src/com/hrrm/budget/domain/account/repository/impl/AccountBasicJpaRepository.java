package com.hrrm.budget.domain.account.repository.impl;

import javax.persistence.EntityManager;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.transaction.control.TransactionControl;
import org.osgi.service.transaction.control.jpa.JPAEntityManagerProvider;
import com.hrrm.budget.domain.account.AccountBaseEntity;
import com.hrrm.infrastructure.persistence.JpaRepository;
import com.hrrm.infrastructure.persistence.impl.JpaRepositoryImpl;

public abstract class AccountBasicJpaRepository<Entity extends AccountBaseEntity>
    extends JpaRepositoryImpl<Entity, Integer> implements JpaRepository<Entity, Integer> {

  @Reference(target = "(name=accounts)")
  protected JPAEntityManagerProvider jpaPprovider;

  @Reference
  protected TransactionControl txControl;

  private EntityManager entityManager;

  @Activate
  public void activate() {
    entityManager = jpaPprovider.getResource(txControl);
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
