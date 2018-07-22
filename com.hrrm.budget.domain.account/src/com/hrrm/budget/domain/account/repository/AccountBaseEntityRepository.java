package com.hrrm.budget.domain.account.repository;

import com.hrrm.budget.domain.account.AccountBaseEntity;
import com.hrrm.infrastructure.persistence.JpaRepository;

public interface AccountBaseEntityRepository<T extends AccountBaseEntity> extends JpaRepository<T, Integer> {

}
