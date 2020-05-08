package com.codingvine.core.sqljpa.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.codingvine.core.sqljpa.entity.AbstractJpaEntity;

@NoRepositoryBean
public interface AbstractJpaRepository<T extends AbstractJpaEntity, I extends Serializable> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {

	T findFirstByUuid(String uuid);

	T findFirstByUuidAndStatus(String uuid, boolean status);

	List<T> findByUuidIn(Collection<String> uuids);

	List<T> findByUuidInAndStatus(Collection<String> uuids, boolean status);

	Page<T> findByStatus(boolean status, Pageable pageable);

	List<T> findByStatus(boolean status);

	long countByStatus(boolean status);

	Boolean existsByUuid(String uuid);

	Boolean existsByUuidAndStatus(String uuid, boolean status);
}