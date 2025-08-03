package com.matchmyjob.domain.api.core.validators;

import com.matchmyjob.domain.api.core.models.errors.ValidationError;
import io.vavr.Function1;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

@FunctionalInterface
public interface ValidatorService<T> extends Function1<T, Validation<Seq<ValidationError>, T>> {

  default Validation<Seq<ValidationError>, T> apply(T t) {
    return validate(t);
  }

  Validation<Seq<ValidationError>, T> validate(T value);
}
