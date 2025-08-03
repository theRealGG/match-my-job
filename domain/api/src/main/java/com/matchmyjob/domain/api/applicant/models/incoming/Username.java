package com.matchmyjob.domain.api.applicant.models.incoming;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Username(String username) {
  public static final int MIN_LENGTH = 6;
}
