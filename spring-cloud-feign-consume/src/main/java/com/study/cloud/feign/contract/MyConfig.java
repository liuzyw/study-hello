package com.study.cloud.feign.contract;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

@Configuration
public class MyConfig {

	/**
	 * config.feign 中 FooConfiguration 用一个
	 * @return
	 */
	@Bean
	public Contract feignContract() {
		return new MyContract();
	}
}
