package com.matchmyjob.configuration;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ObjectMapperConfiguration {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .setSerializationInclusion(NON_NULL)
        .configure(WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModules(new JavaTimeModule(), new Jdk8Module(), new VavrModule());
  }
}
