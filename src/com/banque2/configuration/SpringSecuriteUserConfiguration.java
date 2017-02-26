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
    //DROITS SELON LES PAGES
      http.authorizeRequests()
        .antMatchers("/").access("hasRole('USER')")
        .antMatchers("/apercu/**").access("hasRole('CLIENT')")
        .antMatchers("/transfertIn/**").access("hasRole('CLIENT')")
        .antMatchers("/transfertOut/**").access("hasRole('CLIENT')")
        .antMatchers("/credit/**").access("hasRole('CLIENT')")
        .antMatchers("/profil/**").access("hasRole('CLIENT')")
        .antMatchers("/secureAdmin/**").access("hasRole('ADMIN')")
        .antMatchers("/homeAdmin/**").access("hasRole('ADMIN')")
        .antMatchers("/newAdmin/**").access("hasRole('ADMIN')")
        .antMatchers("/showAccount/**").access("hasRole('ADMIN')")
        .antMatchers("/newClient/**").access("hasRole('ADMIN')")
        .antMatchers("/showAllClient/**").access("hasRole('ADMIN')")
        .antMatchers("/adminProfil/**").access("hasRole('ADMIN')")
        .antMatchers("/addAdmin/**").access("hasRole('ADMIN')")
        .antMatchers("/addAccountClient/**").access("hasRole('ADMIN')")
        .antMatchers("/delAccount/**").access("hasRole('ADMIN')")
        .antMatchers("/updateProfilAdmin/**").access("hasRole('ADMIN')")
        
        .and().formLogin().loginPage("/portail").successHandler(customSuccessHandler)
        .usernameParameter("idt").passwordParameter("pswd")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/portail");
	      //PROPRIETES DE SPRING PERMETTTANT DE CONFIGURER LE MAX DE SESSION 
	      http.sessionManagement().sessionFixation().migrateSession();
	  	  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
	 	  http.sessionManagement().maximumSessions(1);

    }
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/api/**");
    }
 
}