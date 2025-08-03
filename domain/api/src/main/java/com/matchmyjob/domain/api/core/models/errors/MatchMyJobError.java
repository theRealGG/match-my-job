package com.matchmyjob.domain.api.core.models.errors;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum MatchMyJobError implements Error {
  SERVICE("MMJ-499", "A exception in the up- or downstream service occurred"),
  UNKNOWN("MMJ-500", "A unknown exception occurred");
  private final String errorCode;
  private final String message;
}
