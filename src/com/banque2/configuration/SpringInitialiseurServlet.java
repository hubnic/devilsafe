package com.banque2.configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringInitialiseurServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

		   @Override
		    protected Class<?>[] getRootConfigClasses() {
		        return new Class[] { SpringMvcResolver.class };
		    }
		  
		    @Override
		    protected Class<?>[] getServletConfigClasses() {
		        return null;
		    }
		  
		    @Override
		    protected String[] getServletMappings() {
		        return new String[] { "/" };
		    }
}

