package com.codingvine.core.sqljpa.service.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.codingvine.core.base.exception.CodingVineException;
import com.codingvine.core.sqljpa.entity.AbstractJpaEntity;
import com.codingvine.core.sqljpa.repository.AbstractJpaRepository;
import com.codingvine.core.sqljpa.service.AbstractJpaService;
import com.google.common.collect.Lists;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractJpaServiceImpl<T extends AbstractJpaEntity, I extends Serializable, R extends AbstractJpaRepository<T, I>>
		implements AbstractJpaService<T, I> {

	protected abstract R getJpaRepository();

	@Override
	public T save(T entity) {
		return getJpaRepository().save(entity);
	}

	@Override
	public T save(T entity, boolean logEntity) {
		T savedEntity = getJpaRepository().save(entity);
		if (logEntity) {
			archive(entity);
		}
		return savedEntity;
	}

	@Override
	public T saveAndFlush(T entity) {
		return getJpaRepository().saveAndFlush(entity);
	}

	@Override
	public T saveAndFlush(T entity, boolean logEntity) {
		T savedEntity = getJpaRepository().saveAndFlush(entity);
		if (logEntity) {
			archive(entity);
		}
		return savedEntity;
	}

	@Override
	public T update(T entity) {
		return getJpaRepository().save(entity);
	}

	@Override
	public T update(T entity, boolean logEntity) {
		T updatedEntity = getJpaRepository().save(entity);
		if (logEntity) {
			archive(updatedEntity);
		}
		return updatedEntity;
	}

	@Override
	public T updateAndFlush(T entity) {
		return getJpaRepository().saveAndFlush(entity);
	}

	@Override
	public T updateAndFlush(T entity, boolean logEntity) {
		T updatedEntity = getJpaRepository().saveAndFlush(entity);
		if (logEntity) {
			archive(updatedEntity);
		}
		return updatedEntity;
	}

	@Override
	public List<T> save(Collection<T> entities) {
		return getJpaRepository().saveAll(entities);
	}

	@Override
	public List<T> saveAll(Collection<T> entities) {
		return getJpaRepository().saveAll(entities);
	}


	@Override
	public List<T> save(Collection<T> entities, boolean logEntity) {
		List<T> savedEntities = getJpaRepository().saveAll(entities);
		if (logEntity) {
			entities.forEach(this::archive);
		}
		return savedEntities;
	}

	@Override
	public List<T> saveAndFlush(Collection<T> entities) {
		List<T> savedEntities = getJpaRepository().saveAll(entities);
		getJpaRepository().flush();

		return savedEntities;
	}

	@Override
	public List<T> saveAndFlush(Collection<T> entities, boolean logEntity) {
		List<T> savedEntities = getJpaRepository().saveAll(entities);
		getJpaRepository().flush();
		if (logEntity) {
			entities.forEach(this::archive);
		}
		return savedEntities;
	}

	@Override
	public List<T> update(Collection<T> entities) {
		return getJpaRepository().saveAll(entities);
	}

	@Override
	public List<T> update(Collection<T> entities, boolean logEntity) {
		List<T> updatedEntities = getJpaRepository().saveAll(entities);
		if (logEntity) {
			updatedEntities.forEach(this::archive);
		}
		return updatedEntities;
	}

	@Override
	public List<T> updateAndFlush(Collection<T> entities) {
		List<T> updatedEntities = getJpaRepository().saveAll(entities);
		getJpaRepository().flush();

		return updatedEntities;
	}

	@Override
	public List<T> updateAndFlush(Collection<T> entities, boolean logEntity) {
		List<T> updatedEntities = getJpaRepository().saveAll(entities);
		getJpaRepository().flush();
		if (logEntity) {
			updatedEntities.forEach(this::archive);
		}
		return updatedEntities;
	}

	@Override
	public long count() {
		return getJpaRepository().count();
	}

	@Override
	public long countByStatus(boolean status) {
		return getJpaRepository().countByStatus(status);
	}

	@Override
	public T find(I id) {
		return getJpaRepository().findById(id).orElse(null);
	}

	@Override
	public List<T> find(Collection<I> ids) {
		return getJpaRepository().findAllById(ids);
	}

	@Override
	public List<T> findAll() {
		return getJpaRepository().findAll();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return getJpaRepository().findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getJpaRepository().findAll(pageable);
	}

	@Override
	public Page<T> findAllByStatus(boolean status, Pageable pageable) {
		return getJpaRepository().findByStatus(status, pageable);
	}

	@Override
	public List<T> findAllByStatus(boolean status) {
		return getJpaRepository().findByStatus(status);
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return getJpaRepository().findAll(spec, pageable);
	}
	
	@Override
	public List<T> findAll(Specification<T> spec) {
		return getJpaRepository().findAll(spec);
	}

	@Override
	public List<T> findList(List<I> ids) {
		return Lists.newArrayList(getJpaRepository().findAllById(ids));
	}

	@Override
	public List<T> findAllList() {
		return Lists.newArrayList(getJpaRepository().findAll());
	}

	@Override
	public T findByUuid(String uuid) {
		return getJpaRepository().findFirstByUuid(uuid);
	}

	@Override
	public T findByUuidAndStatus(String uuid, boolean status) {
		return getJpaRepository().findFirstByUuidAndStatus(uuid, status);
	}

	@Override
	public List<T> findByUuidIn(Collection<String> uuids) {
		return getJpaRepository().findByUuidIn(uuids);
	}

	@Override
	public List<T> findByUuidInAndStatus(Collection<String> uuids, boolean status) {
		return getJpaRepository().findByUuidInAndStatus(uuids, status);
	}

	@Override
	public Boolean existsByUuid(String uuid) {
		return getJpaRepository().existsByUuid(uuid);
	}

	@Override
	public Boolean existsByUuidAndStatus(String uuid, boolean status) {
		return getJpaRepository().existsByUuidAndStatus(uuid, status);
	}

	@Override
	public void flush() {
		getJpaRepository().flush();
	}

	@Override
	public void delete(T entity) {
		getJpaRepository().delete(entity);
	}

	@Override
	public void delete(Collection<T> entities) {
		getJpaRepository().deleteAll(entities);
	}

	@Override
	public void delete(I id) {
		getJpaRepository().deleteById(id);
	}

	@Override
	public void deleteAll() {
		getJpaRepository().deleteAll();
	}

	@Override
	public void archive(Collection<T> entities) {
		try {
			if (entities != null && !entities.isEmpty()) {
				List<T> archivedEntities = new ArrayList<>();
				for (T entity : entities) {
					archivedEntities.add(convertToLog(entity));
				}
				if (!archivedEntities.isEmpty()) {
					getJpaRepository().saveAll(archivedEntities);
				}
			}
		} catch (Exception e) {
			log.error("Exception while creating log class: ", e);
		}
	}

	/**
	 * Method to log the current entity in log table
	 * 
	 * @param entity
	 */
	@Override
	public void archive(T entity) {
		try {
			T archiveEntity = convertToLog(entity);
			getJpaRepository().saveAndFlush(archiveEntity);
		} catch (Exception e) {
			log.error("Exception while creating log class " + entity.getClass().getName() + "Log.", e);
		}
	}

	@SuppressWarnings("unchecked")
	private T convertToLog(T entity) {
		try {
			String[] ignoreProperties = getPropertyNamesToIgnore(entity);
			String classSimpleName = entity.getClass().getSimpleName();
			String className = entity.getClass().getName().replace(classSimpleName, "log." + classSimpleName + "Log");
			Object logEntity = Class.forName(className).newInstance();
			BeanUtils.copyProperties(entity, logEntity, ignoreProperties);
			return (T) logEntity;
		} catch (InstantiationException
				| IllegalAccessException
				| ClassNotFoundException e) {
			log.error("Error while converting entity to log entity: ", e);
			throw new CodingVineException("Exception while creating log entity: ", e);
		}
	}

	private String[] getPropertyNamesToIgnore(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (Objects.isNull(srcValue)) {
				emptyNames.add(pd.getName());
			}
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}