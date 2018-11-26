package com.qa.CVManager.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.qa.CVManager.Business.Service.MongoUserDetailsService;
import com.qa.CVManager.Constants.Constants;

@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	MongoUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests().antMatchers(HttpMethod.POST, "/api/createaccount/user").permitAll()
			.and().authorizeRequests()
			.antMatchers(Constants.MAPPING_PATHS_ADMIN).hasAuthority(Constants.ACCOUNT_TYPE_ADMIN)
			.antMatchers(Constants.MAPPING_PATHS_TRAINEEMANGER).hasAuthority(Constants.ACCOUNT_TYPE_TRAINEEMANAGER)		
			.antMatchers(Constants.MAPPING_PATHS_SALES).hasAuthority(Constants.ACCOUNT_TYPE_SALES)		
			.antMatchers(Constants.MAPPING_PATHS_TRAINEE).hasAuthority(Constants.ACCOUNT_TYPE_TRAINEE)
			.anyRequest().authenticated()
			.and().formLogin().loginPage(Constants.REACT_LOGIN_PAGE_URL)
					.failureUrl(Constants.REACT_FAILURE_LOGIN_PAGE_URL)
			.and().logout().logoutUrl("/api/logout").deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true)
			.and().httpBasic()
			.and().sessionManagement().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService);
	}
}
