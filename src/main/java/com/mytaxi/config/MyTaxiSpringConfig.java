package com.mytaxi.config;

/**
 * @author Mostafa El-Gazzar.
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class MyTaxiSpringConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/v1/**").authenticated();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		/* http.authorizeRequests().antMatchers("/").permitAll(); */ // To pass All requests
	}
}
