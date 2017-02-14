package com.banque2.configuration;

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

import com.banque2.services.ServiceDaoAdministrateur;

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
		    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/banque2");
		    driverManagerDataSource.setUsername("root");
		    driverManagerDataSource.setPassword("qwerty");
		    return driverManagerDataSource;
		}
	    
	    @Bean
		public ServiceDaoAdministrateur getServiceDAO() {
			return new ServiceDaoAdministrateur(dataSource());
		}
	    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/librairie/**").addResourceLocations("/WEB-INF/lib/");
    }


}