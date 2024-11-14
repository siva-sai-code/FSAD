package com.bookexchange.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.requestMatchers("/register").permitAll()
				.requestMatchers("/resources/**").permitAll()
				.requestMatchers("/home").permitAll()
				.anyRequest().authenticated())
		.formLogin(form -> form.loginPage("/login").usernameParameter("email")
				.loginProcessingUrl("/login").defaultSuccessUrl("/home", true).permitAll())
		.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll());
		return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

	}
	
}
