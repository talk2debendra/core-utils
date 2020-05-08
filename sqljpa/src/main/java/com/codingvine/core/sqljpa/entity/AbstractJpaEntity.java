package com.codingvine.core.sqljpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.codingvine.core.base.utils.CodingVineUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Abstract JPA class with generic ID. All models will be extending this class
 *
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({ CodingVineEntityListener.class })
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class AbstractJpaEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "uuid", columnDefinition = "char(40)", unique = true, nullable = false)
	protected String uuid;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdAt;

	@Column(name = "created_by", columnDefinition = "char(40)")
	protected String createdBy;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updatedAt;

	@Column(name = "updated_by", columnDefinition = "char(40)")
	protected String updatedBy;

	@Builder.Default
	@Column(name = "status", columnDefinition = "bit(1) default 1")
	protected boolean status = true;

	@PrePersist
	private void validationBeforeSaving() {
		if (StringUtils.isBlank(uuid)) {
			uuid = CodingVineUtils.generateUniqueId();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractJpaEntity other = (AbstractJpaEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}

}