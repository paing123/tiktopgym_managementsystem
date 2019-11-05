package com.tiktop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tiktop.services.MemberService;

@EnableWebMvc
@EnableWebSecurity
public class MyWebApplication extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private MemberService memberService;

	// configure the request
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/member/**").hasRole("MEMBER")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.and()
		.formLogin().loginPage("/login")
		.successHandler(authenticationSuccessHandler)// for route after login
		.and()
		.logout().logoutUrl("/logout");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/fonts/**", 
							"/css/**", 
							"/trainer-image/**", 
							"/js/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/",
							  "classpath:/static/fonts/", 
							  "classpath:/static/css/",
							  "classpath:/static/trainer-image/", 
							  "classpath:/static/js/");

	}

	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(memberService);
        auth.setPasswordEncoder(encoder);
        return auth;
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
