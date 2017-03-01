package com.banque2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
 
@Configuration
@EnableWebSecurity
public class SpringSecuriteUserConfiguration extends WebSecurityConfigurerAdapter {
 

	@Autowired
	private SpringCustomAuthentificationProvider springAuthenticationProvider;
	
    @Autowired
    SpringSuccessHandler customSuccessHandler;
 
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.inMemoryAuthentication().withUser("client").password("client").roles("CLIENT");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
       
        //Permet de gerer l'authentification se le login et le mdp saisi
        auth.authenticationProvider(springAuthenticationProvider);

    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    //GESTION DES SESSIONS
    	   //PROPRIETES DE SPRING PERMETTTANT DE CONFIGURER LE MAX DE SESSION 
	      http.sessionManagement().sessionFixation().migrateSession();
	 	  //http.sessionManagement().maximumSessions(1);
	      http.sessionManagement().invalidSessionUrl("/portail");
	      http.sessionManagement().maximumSessions(1);
	      
    //DROITS SELON LES PAGES
      http.authorizeRequests()
        .antMatchers("/").access("hasRole('USER')")
        .antMatchers("/apercu/**").access("hasRole('CLIENT')")
        .antMatchers("/transfertIn/**").access("hasRole('CLIENT')")
        .antMatchers("/transfertOut/**").access("hasRole('CLIENT')")
        .antMatchers("/credit/**").access("hasRole('CLIENT')")
        .antMatchers("/profil/**").access("hasRole('CLIENT')")
        .antMatchers("/secureAdmin/**").access("hasRole('ADMIN')")
        .antMatchers("/homeAdmin/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/newAdmin/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/showAccount/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/newClient/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/showAllClient/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/adminProfil/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/addAdmin/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/addAccountClient/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/delAccount/**").access("hasRole('ADMIN_AUTH')")
        .antMatchers("/updateProfilAdmin/**").access("hasRole('ADMIN_AUTH')")
        
        .and().formLogin().loginPage("/portail").successHandler(customSuccessHandler)
        .usernameParameter("idt").passwordParameter("pswd")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/portail");
	   

    }
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/api/**");
    }
 
}