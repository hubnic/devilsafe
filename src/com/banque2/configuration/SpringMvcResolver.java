package com.banque2.configuration;

import com.banque2.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.banque2")
public class SpringMvcResolver extends WebMvcConfigurerAdapter {
	
	 public void configureViewResolvers(ViewResolverRegistry registry) {
		 
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/vue/");
	        viewResolver.setSuffix(".jsp");
	        registry.viewResolver(viewResolver);
	    }
	 
	 
	    @Bean(name = "dataSource")
		public DriverManagerDataSource dataSource() {
		   DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		    driverManagerDataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net:3306/heroku_2ea2dde16e81c62");
		    driverManagerDataSource.setUsername("b3851ccc9becd8");
		    driverManagerDataSource.setPassword("41593512");
		    return driverManagerDataSource;
		}
	    
	 
	    /*
	   @Bean(name = "dataSource")
			public DriverManagerDataSource dataSource() {
			    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
			    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
			    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/heroku_2ea2dde16e81c62");
			    driverManagerDataSource.setUsername("root");
			    driverManagerDataSource.setPassword("qwerty");
			    return driverManagerDataSource;
			}
			*/
	    @Bean
		public ServiceDaoAdministrateur getSServiceDaoAdministrateur() {
			return new ServiceDaoAdministrateur(dataSource());
		}
	    
	    @Bean
	    public ServiceAuthentification getServiceAuthentification() {
			return new ServiceAuthentification(dataSource());
		}
	    
	    @Bean
	    public ServiceDaoClient getServiceDaoClient() {
			return new ServiceDaoClient(dataSource());
		}

	    @Bean
	    public ServiceDaoApi getServiceDaoApi() {
			return new ServiceDaoApi(dataSource());
		}

		@Bean
		public ServiceDaoCompte getServiceDaoCompte() {
		return new ServiceDaoCompte(dataSource());
	}


	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/librairie/**").addResourceLocations("/WEB-INF/lib/");
    }


}