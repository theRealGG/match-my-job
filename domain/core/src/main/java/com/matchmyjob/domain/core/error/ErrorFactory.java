package com.matchmyjob.domain.core.error;

import com.matchmyjob.domain.api.core.models.errors.MatchMyJobError;
import com.matchmyjob.domain.api.core.models.errors.ValidationError;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorFactory {

  public ValidationError validationError(String message, Object... objects) {
    return new ValidationError(message, objects);
  }

  public MatchMyJobError serviceError() {
    return MatchMyJobError.SERVICE;
  }

  public MatchMyJobError unknownError() {
    return MatchMyJobError.UNKNOWN;
  }
}
