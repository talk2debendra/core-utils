package com.codingvine.core.sqljpa.entity;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;

import com.codingvine.core.base.utils.CodingVineUtils;
import com.codingvine.core.sqljpa.SecurityUtil;

/**
 * Entity listener for all Entities/Models
 *
 */
public class CodingVineEntityListener {

	// This code will be executed before every insert into DB
	@PrePersist
	private void beforeInsert(AbstractJpaEntity abstractEntity) {
		if (abstractEntity.getCreatedAt() == null) {
			abstractEntity.setCreatedAt(new Date());
		}
		if (abstractEntity.getUpdatedAt() == null) {
			abstractEntity.setUpdatedAt(new Date());
		}

		if (StringUtils.isBlank(abstractEntity.getUuid())) {
			abstractEntity.setUuid(CodingVineUtils.generateUniqueId());
		}

		if (StringUtils.isBlank(abstractEntity.getCreatedBy())) {
			abstractEntity.setCreatedBy(SecurityUtil.getCurrentUserId());
		}
	}

	// This code will be executed before every update into DB
	@PreUpdate
	private void beforeUpdate(AbstractJpaEntity abstractEntity) {
		if (abstractEntity.getCreatedAt() == null) {
			abstractEntity.setCreatedAt(new Date());
		}

		if (StringUtils.isNotBlank(SecurityUtil.getCurrentUserId())) {
			abstractEntity.setUpdatedBy(SecurityUtil.getCurrentUserId());
		}

		abstractEntity.setUpdatedAt(new Date());
	}
}