package com.shortify.api.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

// Wrapper class to enable LocalDateTime serialization through JSR310 Jackson module in GraalVM native image

public class ModulatedObjectMapper extends ObjectMapper {
    public ModulatedObjectMapper() {
        super();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        this.registerModule(javaTimeModule).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
