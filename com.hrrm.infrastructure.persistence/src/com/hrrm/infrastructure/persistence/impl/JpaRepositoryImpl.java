package com.hrrm.infrastructure.persistence.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.osgi.service.transaction.control.TransactionControl;
import com.hrrm.infrastructure.persistence.JpaRepository;

public abstract class JpaRepositoryImpl<T, ID> implements JpaRepository<T, ID> {

	protected abstract Class<T> getEntityClass();

	protected abstract EntityManager getEntityManager();

	protected abstract TransactionControl getTxControl();

	@Override
	public List<T> findAll() {
		return getTxControl().notSupported(() -> {
			return getFindAllQuery().getResultList();
		});
	}

	private TypedQuery<T> getFindAllQuery() {
		EntityManager entityManager = getEntityManager();
		CriteriaQuery<T> criteria = getEntityCriteriaQuery(entityManager);
		TypedQuery<T> findAllQuery = entityManager.createQuery(criteria);
		return findAllQuery;
	}

	private CriteriaQuery<T> getEntityCriteriaQuery(EntityManager entityManager) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getEntityClass());
		return criteria;
	}

	@Override
	public Optional<T> find(ID id) {
		return getTxControl().supports(() -> {
			EntityManager entityManager = getEntityManager();
			return Optional.ofNullable(entityManager.find(getEntityClass(), id));
		});
	}

}
