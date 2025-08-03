package com.matchmyjob.domain.api.applicant.models.dtos;

import com.matchmyjob.domain.api.core.models.dtos.NameDTO;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record ApplicantDTO(NameDTO name) {}
