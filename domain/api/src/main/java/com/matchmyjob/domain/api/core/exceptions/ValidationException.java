package com.matchmyjob.domain.api.core.exceptions;

import com.matchmyjob.domain.api.core.models.errors.ValidationError;
import io.vavr.collection.Seq;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ValidationException extends BusinessException {

  private final Seq<ValidationError> validationErrors;
}
