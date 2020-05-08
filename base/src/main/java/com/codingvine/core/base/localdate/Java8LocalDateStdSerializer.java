/**
 * 
 */
package com.codingvine.core.base.localdate;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 */
public class Java8LocalDateStdSerializer extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = 1L;

	public Java8LocalDateStdSerializer() {
		super(LocalDate.class);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeObject(value.toString());
	}

}