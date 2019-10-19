package com.tiktop;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@EnableWebSecurity
public class MyWebApplication extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private DataSource dataSource;
	
	//configure the request
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/member/**").hasRole("MEMBER")
			.antMatchers("/admin/**").hasRole("ADMIN")
		.and()
			.formLogin()
			.loginPage("/login")
			.successHandler(authenticationSuccessHandler)//for route after login
		.and()
			.logout()
			.logoutUrl("/logout");
	}
	
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler(
	                "/fonts/**",
	                "/css/**",
	                "/trainer-image/**",
	                "/js/**")
	                .addResourceLocations(
	                        "classpath:/META-INF/resources/webjars/",
	                        "classpath:/static/fonts/",
	                        "classpath:/static/css/",
	                        "classpath:/static/trainer-image/",
	                        "classpath:/static/js/");
	        
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth
	.jdbcAuthentication()
		.passwordEncoder(encoder) //to encode password
		.dataSource(dataSource)
		.usersByUsernameQuery("select login,password,enable from member where login=?") //to search users
 		.authoritiesByUsernameQuery("select login,role from member where login=?");//to search role
	}
	
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
}
