package com.lichkin.application.services.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lichkin.application.services.LKSmsSender;

@Configuration
public class NoSmsSender {

	@Bean
	@ConditionalOnMissingBean
	public LKSmsSender smsSender() {
		return (locale, busType, subBusType, cellphone, smsContent, replaceParams) -> {
		};
	}

}
