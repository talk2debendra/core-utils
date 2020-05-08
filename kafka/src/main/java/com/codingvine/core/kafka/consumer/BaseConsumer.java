package com.codingvine.core.kafka.consumer;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import com.codingvine.core.base.constants.CodingVineConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseConsumer<T> {

	@Autowired
	protected ObjectMapper objectMapper;

	protected T getDto(ConsumerRecord<String, String> record) {

		record.headers().forEach(header -> {
			if (CodingVineConstants.MESSAGE_ID.equalsIgnoreCase(header.key()))
				MDC.put(CodingVineConstants.GUID, new String(header.value()));
		});

		T data = null;

		log.debug("BaseConsumer:: Received Payload= " + record);

		try {

			if (StringUtils.isNotBlank(record.key())) {

				@SuppressWarnings("unchecked")
				Class<T> clazz = (Class<T>) Class.forName(record.key());

				log.debug("BaseConsumer:: Retrived class: " + clazz);

				data = objectMapper.readValue(record.value(), clazz);

				log.info("BaseConsumer:: Retrieved object: " + data);

			} else {
				log.warn("BaseConsumer:: Kafka Record Missing key");
			}

		} catch (Exception e) {
			log.error("BaseConsumer:: Error casting Kafka record to desrired dto: ", e);
		} finally {
			MDC.remove(CodingVineConstants.GUID);
		}

		return data;
	}

}