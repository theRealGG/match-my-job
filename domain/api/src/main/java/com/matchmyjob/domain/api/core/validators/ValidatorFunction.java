package com.matchmyjob.domain.api.core.validators;

import com.matchmyjob.domain.api.core.models.errors.ValidationError;
import io.vavr.Function1;
import io.vavr.control.Validation;

@FunctionalInterface
public interface ValidatorFunction<T> extends Function1<T, Validation<ValidationError, T>> {}
