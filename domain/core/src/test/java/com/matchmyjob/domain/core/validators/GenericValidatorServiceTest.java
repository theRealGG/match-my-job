package com.matchmyjob.domain.core.validators;

import static io.vavr.control.Validation.invalid;
import static org.assertj.core.api.Assertions.assertThat;

import com.matchmyjob.domain.api.core.models.errors.ValidationError;
import com.matchmyjob.domain.api.core.validators.ValidatorFunction;
import io.vavr.collection.List;
import io.vavr.control.Validation;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GenericValidatorServiceTest {

  private static final ValidatorFunction<String> PASS_FUNCTION = Validation::valid;
  private static final ValidatorFunction<String> FAIL_FUNCTION =
      s -> invalid(new ValidationError("A error occurred"));

  @Test
  @DisplayName("Test factory method of SimpleValidatorService")
  void shouldCreateInstanceViaFactoryMethod() {
    var validator = GenericValidatorService.of(PASS_FUNCTION);
    assertThat(validator).isNotNull();
  }

  @Test
  @DisplayName("validator service should compute the validation result correctly for happy path")
  void shouldComputeValidationResultCorrectly() {
    var validator = GenericValidatorService.of(PASS_FUNCTION);
    VavrAssertions.assertThat(validator.validate("")).isValid();
  }

  @Test
  @DisplayName(
      "validator service should compute the validation result correctly for invalid argument")
  void shouldComputeValidationResultCorrectlyForInvalidArgument() {
    var validator =
        GenericValidatorService.of(
            s -> invalid(new ValidationError("First Error")),
            s -> invalid(new ValidationError("Second Error")));
    VavrAssertions.assertThat(
            validator
                .validate("")
                .mapError(seq -> List.ofAll(seq.map(ValidationError::getMessage))))
        .isInvalid()
        .extracting(Validation::getError)
        .isEqualTo(List.of("First Error", "Second Error"));
  }

  @Test
  @DisplayName(
      "validator service should compute the validation result correctly for multiple errors")
  void shouldComputeValidationResultCorrectlyForMultipleErrors() {
    var validator = GenericValidatorService.of(FAIL_FUNCTION);
    VavrAssertions.assertThat(
            validator
                .validate("")
                .mapError(seq -> List.ofAll(seq.map(ValidationError::getMessage))))
        .isInvalid()
        .extracting(Validation::getError)
        .isEqualTo(List.of("A error occurred"));
  }
}
