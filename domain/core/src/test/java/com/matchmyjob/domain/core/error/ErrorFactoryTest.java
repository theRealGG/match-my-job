package com.matchmyjob.domain.core.error;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchmyjob.domain.api.core.models.errors.MatchMyJobError;
import com.matchmyjob.domain.api.core.models.errors.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ErrorFactoryTest {

  @Test
  @DisplayName("factory should construct ValidationError")
  void shouldConstructValidationError() {
    assertThat(
            ErrorFactory.validationError(
                "This is my error. parameter={}, parameter={}", "first", "second"))
        .usingRecursiveComparison()
        .isEqualTo(new ValidationError("This is my error. parameter=first, parameter=second"));
  }

  @Test
  @DisplayName("factory should construct ServiceError")
  void shouldConstructServiceError() {
    assertThat(ErrorFactory.serviceError()).isEqualTo(MatchMyJobError.SERVICE);
  }

  @Test
  @DisplayName("factory should construct UnknownError")
  void shouldConstructUnknownError() {
    assertThat(ErrorFactory.unknownError()).isEqualTo(MatchMyJobError.UNKNOWN);
  }
}
