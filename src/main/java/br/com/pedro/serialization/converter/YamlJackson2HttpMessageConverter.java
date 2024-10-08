package br.com.pedro.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

	public YamlJackson2HttpMessageConverter() {
		 super(new YAMLMapper().registerModule(new JavaTimeModule()).setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType("application/x-yaml"));
	}

}
