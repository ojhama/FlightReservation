package com.akash.flightreservation.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		System.out.println("AuthenticationManager bean created");
		return super.authenticationManagerBean();
	}

	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/showReg", "/", "index.html", "/registerUser", "/login", "/showLogin", "/login/*",
						"/reservations/*")
				.permitAll().antMatchers("/admin/showAddFlight").hasAnyAuthority("ADMIN").anyRequest().authenticated()
				.and().csrf().disable();
	}
}
/*
 * public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 * 
 * @Bean public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Override
 * 
 * @Bean(name=BeanIds.AUTHENTICATION_MANAGER) public AuthenticationManager
 * authenticationManagerBean() throws Exception {
 * System.out.println("AuthenticationManager bean created"); return
 * super.authenticationManagerBean(); } public void configure(HttpSecurity http)
 * throws Exception { // http.authorizeRequests() // .antMatchers("/showReg",
 * "/", "index.html", "/registerUser", "/login", "/showLogin",
 * "/login/*","/reservations/*") //
 * .permitAll().antMatchers("/admin/showAddFlight").hasAnyAuthority("ADMIN").
 * anyRequest().authenticated().and().csrf().disable();
 * 
 * http.authorizeRequests() .antMatchers("*")
 * .permitAll().and().csrf().disable(); } }
 */