package com.sap.employeeslist.services.common;

import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * Custom Jackson Jaxb Json Provider.
 * 
 * @author fabiano.rosa
 *
 */
@Provider
public class CustomJacksonJaxbJsonProvider extends JacksonJaxbJsonProvider {
	private static final Logger logger = LoggerFactory.getLogger(CustomJacksonJaxbJsonProvider.class);
	private static ObjectMapper commonMapper;

	/**
	 * Empty constructor for CustomJacksonJaxbJsonProvider.
	 */
	public CustomJacksonJaxbJsonProvider() {

		logger.trace("ENTER CustomJacksonJaxbJsonProvider");
		if (commonMapper == null) {
			ObjectMapper mapper = _mapperConfig.getDefaultMapper();

			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);

			mapper.registerModule(new JaxbAnnotationModule());

			commonMapper = mapper;
		}
		super.setMapper(commonMapper);
	}
}
