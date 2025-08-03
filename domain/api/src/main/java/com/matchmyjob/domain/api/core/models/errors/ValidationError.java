package com.matchmyjob.domain.api.core.models.errors;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
@Getter
public class ValidationError implements Error {

  private final String message;

  public ValidationError(String format, Object... args) {
    message = MessageFormatter.arrayFormat(format, args).getMessage();
  }

  @Override
  public String getErrorCode() {
    return "MMJ-2";
  }
}
