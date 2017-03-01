package com.banque2.configuration;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class SpringSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		// SESSION EXPIRE APRES 5 MIN

		request.getSession().setMaxInactiveInterval(300);
	    String targetUrl = determineTargetUrl(authentication);
	    
        if (response.isCommitted()) {
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	
	/**
	 * M�thode qui selon le role utilisateur retourne la page appropriee
	 */
	protected String determineTargetUrl(Authentication authentication) {
		String url = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		if (checkIfClient(roles)) {
			url = "/apercu";	
		}else if (checkIfAdmin(roles)) {
			url = "/secureAdmin";
		}else if (checkIfAdminAuth(roles)) {
			url = "/homeAdmin";
		}else {
			url = "/denied";
		}

		return url;
	}
	private boolean checkIfClient(List<String> roles) {
		if (roles.contains("ROLE_CLIENT")) {
			System.out.print("Ceci est un client");
			return true;
		}
		return false;
	}

	private boolean checkIfAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}
	private boolean checkIfAdminAuth(List<String> roles) {
		if (roles.contains("ROLE_ADMIN_AUTH")) {
			return true;
		}
		return false;
	}
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
	

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}



}
