package com.matchmyjob.incoming.web.graphql.applicant.datafetchers;

import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.applicant.repositories.ApplicantRepository;
import com.matchmyjob.incoming.web.graphql.applicant.mappers.DomainToOutgoingMapper;
import com.matchmymjob.incoming.graphql.applicant.generated.types.Applicant;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@DgsComponent
@RequiredArgsConstructor
@Slf4j
public class ApplicantFetcher {

  private final ApplicantRepository applicantRepository;
  private final DomainToOutgoingMapper domainToOutgoingMapper;

  @DgsQuery
  public Mono<Applicant> applicant(@InputArgument String username) {
    return applicantRepository
        .getByUsername(new Username(username))
        .doFirst(() -> log.info("Retrieving applicant via graphql api"))
        .map(domainToOutgoingMapper::toApplicant);
  }
}
