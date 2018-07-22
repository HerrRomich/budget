package com.hrrm.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
public interface JpaRepository<T, ID> {

	
	List<T> findAll();
	
	Optional<T> find(ID id);

}
