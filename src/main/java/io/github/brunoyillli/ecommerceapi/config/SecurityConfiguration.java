package io.github.brunoyillli.ecommerceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer
				.antMatchers("/api/orders/**")
				.authenticated())
				.oauth2ResourceServer().jwt();
		http.cors();
		http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
		Okta.configureResourceServer401ResponseBody(http);
		http.csrf().disable();
		return http.build();
	}
}
