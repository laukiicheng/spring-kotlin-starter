package com.service.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

class SerializationUtils {
    companion object {
        fun buildJacksonObjectMapper(): ObjectMapper {
            return Jackson2ObjectMapperBuilder.json()
                .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, JsonParser.Feature.STRICT_DUPLICATE_DETECTION)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build<ObjectMapper>()
                .registerKotlinModule()
                .registerModules(JavaTimeModule())
        }
    }
}
