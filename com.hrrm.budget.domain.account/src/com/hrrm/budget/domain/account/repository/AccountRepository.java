package com.hrrm.budget.domain.account.repository;

import com.hrrm.budget.domain.account.Account;
import com.hrrm.infrastructure.persistence.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
