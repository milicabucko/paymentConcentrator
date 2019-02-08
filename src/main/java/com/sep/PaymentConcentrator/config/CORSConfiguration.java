package com.sep.PaymentConcentrator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfiguration extends CorsFilter {

	 public CORSConfiguration() {
	        super(configurationSource());
	    }

	    private static UrlBasedCorsConfigurationSource configurationSource() {
	        CorsConfiguration config = new CorsConfiguration();
	        config.addAllowedOrigin("*");
	        config.addAllowedMethod("*");
	        config.addAllowedHeader("*");
	        config.setAllowCredentials(true);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);
	        return source;
	    }

}