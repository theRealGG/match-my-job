package com.matchmyjob.domain.api.applicant.repositories;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import reactor.core.publisher.Mono;

public interface ApplicantRepository {
  Mono<ApplicantDTO> getByUsername(Username username);
}
