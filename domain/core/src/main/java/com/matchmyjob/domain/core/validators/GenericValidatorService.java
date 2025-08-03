package com.matchmyjob.domain.core.validators;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;

import com.matchmyjob.domain.api.core.models.errors.ValidationError;
import com.matchmyjob.domain.api.core.validators.ValidatorFunction;
import com.matchmyjob.domain.api.core.validators.ValidatorService;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public class GenericValidatorService<T> implements ValidatorService<T> {
  private final List<ValidatorFunction<T>> validatorFunctions;

  @SafeVarargs
  public GenericValidatorService(ValidatorFunction<T>... validatorFunction) {
    this.validatorFunctions = List.of(validatorFunction);
  }

  public GenericValidatorService(Seq<ValidatorFunction<T>> validatorFunctions) {
    this.validatorFunctions = List.ofAll(validatorFunctions);
  }

  @SafeVarargs
  public static <T> ValidatorService<T> of(ValidatorFunction<T>... validatorFunction) {
    return new GenericValidatorService<>(validatorFunction);
  }

  @Override
  public Validation<Seq<ValidationError>, T> validate(T value) {
    var errors =
        validatorFunctions
            .map(v -> v.apply(value))
            .filter(Validation::isInvalid)
            .map(Validation::getError);

    return errors.isEmpty() ? valid(value) : invalid(errors);
  }
}
