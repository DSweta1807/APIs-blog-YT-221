package com.CWS.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.CWS.blog.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService; 
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		// We don't need CSRF for this example
		httpSecurity
				.csrf()
				.disable()
				.authorizeHttpRequests()
//				.requestMatchers(PUBLIC_URLS).permitAll()
				.anyRequest().authenticated().and()
				.httpBasic();

		
//				above all are the type of request
//			we will authorize request by	authorizeHttpRequests() ->will callanyRequest()on this.. 
//				authorizeHttpRequests-> will return (AuthorizationManagerRequestMatcherRegistry) 
//				due to basic authenticate the form which we were getting for security purpose in  html form will be not provided, 
//				its will change to javaScript form (we send the request in js form from browser too)
				
//				****but doing this we have to send at several time user name password... this can be resolved <<JWT AUTHENTICATION>>
//				which is also known as token based authentication to secure APIs
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(this.customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

//Cross-Site Request Forgery (CSRF) is an attack that forces authenticated users to submit a request to a Web application 
//against which they are currently authenticated. CSRF attacks exploit the trust a Web application has in an authenticated user. 
//(Conversely, cross-site scripting (XSS) attacks exploit the trust a user has in a particular Web application). 
//A CSRF attack exploits a vulnerability in a Web application if it cannot differentiate between a 
//request generated by an individual user and a request generated by a user without their consent.

