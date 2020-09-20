package com.gymondo.rest.subscriptionAPI.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

	  @Bean
	  public AuditorAware<String> auditorProvider() {
		  return new AuditorAwareImpl();
	  }
}
