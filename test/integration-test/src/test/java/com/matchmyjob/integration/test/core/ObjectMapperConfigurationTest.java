package com.matchmyjob.integration.test.core;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchmyjob.app.MatchMyJobApp;
import com.matchmyjob.configuration.ObjectMapperConfiguration;
import io.vavr.collection.List;
import java.time.LocalDate;
import lombok.SneakyThrows;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@JsonTest
@ContextConfiguration(classes = MatchMyJobApp.class)
@Import(ObjectMapperConfiguration.class)
class ObjectMapperConfigurationTest {

  @Autowired private ObjectMapper objectMapper;

  @Nested
  class SerializationTest {
    @Test
    @SneakyThrows
    void shouldSerializeDateAsString() {
      var json = objectMapper.writeValueAsString(LocalDate.of(2025, 1, 1));
      assertThat(json).isEqualTo("\"2025-01-01\"");
    }

    @Test
    @SneakyThrows
    void shouldSerializeVavrCollectionAsString() {
      var json = objectMapper.writeValueAsString(List.of("1", "2", "3"));
      assertThat(json).isEqualTo("[\"1\",\"2\",\"3\"]");
    }
  }

  @Nested
  class DeserializationTest {
    @Test
    @SneakyThrows
    void shouldDeserializeDateAsString() {
      var json = objectMapper.readValue("\"2025-01-01\"", LocalDate.class);
      assertThat(json).isEqualTo(LocalDate.of(2025, 1, 1));
    }

    @Test
    @SneakyThrows
    void shouldDeserializeVavrCollectionAsString() {
      var json = objectMapper.readValue("[\"1\",\"2\",\"3\"]", List.class);
      VavrAssertions.assertThat(json).containsExactly("1", "2", "3");
    }
  }
}
