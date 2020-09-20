package com.gymondo.rest.subscriptionAPI.configuration;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		// TODO Auto-generated method stub
		return "root";
	}

}
