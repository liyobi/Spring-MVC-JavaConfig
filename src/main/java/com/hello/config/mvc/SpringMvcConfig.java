package com.hello.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
<mvc:annotation-driven> 
 */
@EnableWebMvc 
/**
<context:component-scan base-package="com.hello">
		
</context:component-scan>
 */
@ComponentScan(basePackages = {"com.hello"})  
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	/**
	<mvc:resources location="/resources/**" mapping="/resources"/>
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(60*60*24);
    }
 
    
    /**
     <mvc:default-servlet-handler>
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }    
     
    
    /**
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
		<property name="prefix" value="/WEB-INF/views/" />
		<property name="suffix" value=".jsp" />
	</bean>
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
   <mvc:view-controller path="test" view-name="test"/>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/test").setViewName("test");
    }
 
}
