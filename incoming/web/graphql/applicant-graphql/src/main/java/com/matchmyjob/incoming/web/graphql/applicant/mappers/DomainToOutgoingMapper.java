package com.matchmyjob.incoming.web.graphql.applicant.mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmymjob.incoming.graphql.applicant.generated.types.Applicant;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface DomainToOutgoingMapper {

  Applicant toApplicant(ApplicantDTO applicant);
}
