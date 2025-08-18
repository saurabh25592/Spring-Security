package com.auth.YTSpringSecurity;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


	// it is authenticate the user is admin or not
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(granted -> granted.getAuthority().equals("ROLE ADMIN")); // if role is admin throw drec home
																					// page

		if (isAdmin) {
			setDefaultTargetUrl("/admin/home");
		} else {
			setDefaultTargetUrl("/user/home");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
