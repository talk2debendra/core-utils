package com.codingvine.core.base.common.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDto {

	protected Long id;

	protected String uuid;

	protected Date createdAt;

	protected String createdBy;

	protected Date updatedAt;

	protected String updatedBy;

	protected Boolean status;

}