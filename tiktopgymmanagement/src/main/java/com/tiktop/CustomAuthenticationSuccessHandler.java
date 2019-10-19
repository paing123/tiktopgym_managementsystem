package com.tiktop;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username       
        
        HttpSession session=httpServletRequest.getSession(false);
        session.setAttribute("currentUser", currentUser);
        
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/trainer");
        } else {
            httpServletResponse.sendRedirect("/bookingSchedule");
        }
    }
}
