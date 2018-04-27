package com.study.cloud.feign.contract;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

@Configuration
public class MyConfig {

	/**
	 * 和 config.feign 中 FooConfiguration 只能用一个
	 *
	 * 这个用的是 SpringMvcContract
	 * @return
	 */
	@Bean
	public Contract feignContract() {
		return new MyContract();
	}
}
