package com.matchmyjob.domain.api.core.models.dtos;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record NameDTO(String firstname, String lastname, String nickname) {}
