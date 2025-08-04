package com.matchmyjob.app.configuration;

import com.matchmyjob.domain.api.applicant.repositories.ApplicantRepository;
import com.matchmyjob.domain.api.applicant.services.ApplicantService;
import com.matchmyjob.domain.core.applicant.services.ApplicantServiceCore;
import com.matchmyjob.domain.core.applicant.services.ApplicantServiceDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ApplicantConfiguration {

  @Bean
  ApplicantService applicantService(ApplicantRepository repository) {
    return new ApplicantServiceDecorator(new ApplicantServiceCore(repository));
  }
}
