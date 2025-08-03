package com.matchmyjob.domain.core.applicant.validators;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.core.models.errors.ValidationError;
import io.vavr.control.Validation;
import java.util.stream.Stream;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

class UsernameValidatorTest {

  private final Faker faker = new Faker();

  @Nested
  @DisplayName("Test username must not be null predicate")
  class TestNonNullPredicate {

    static Stream<Arguments> invalidUsername() {
      return Stream.of(Arguments.of(new Username(null)));
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("invalidUsername")
    @DisplayName("should detect nullable username")
    void testNonNullPredicateWithInvalidUsername(Username username) {
      assertThat(UsernameValidator.NON_NULL.apply(null))
          .isInvalid()
          .extracting(Validation::getError)
          .extracting(ValidationError::getMessage)
          .isEqualTo("No username provided");
    }

    @Test
    @DisplayName("should pass for non null username")
    void testNonNullPredicateWithValidUsername() {
      var username = new Username("username");
      assertThat(UsernameValidator.NON_NULL.apply(username))
          .isValid()
          .extracting(Validation::get)
          .isEqualTo(username);
    }
  }

  @Nested
  @DisplayName("Test username must have min length predicate")
  class MinLengthPredicate {

    @Test
    @DisplayName("should fail for to short username")
    void predicateShouldFailForShortUsername() {
      var username = new Username(faker.lorem().characters(0, Username.MIN_LENGTH - 1));
      assertThat(UsernameValidator.MIN_LENGTH.apply(username))
          .isInvalid()
          .extracting(Validation::getError)
          .extracting(ValidationError::getMessage)
          .isEqualTo("Username length should be at least " + Username.MIN_LENGTH + " characters");
    }

    @Test
    @DisplayName("should pass for username")
    void predicateShouldPassForUsername() {
      var username = new Username(faker.lorem().characters(Username.MIN_LENGTH));
      assertThat(UsernameValidator.MIN_LENGTH.apply(username))
          .isValid()
          .extracting(Validation::get)
          .isEqualTo(username);
    }
  }
}
