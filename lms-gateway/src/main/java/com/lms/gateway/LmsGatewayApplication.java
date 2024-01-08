package com.lms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class LmsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsGatewayApplication.class, args);
		
	}
	
	
	@Bean
	public RouteLocator locator(RouteLocatorBuilder routesBuilder) {
		return routesBuilder.routes()
				.route(x->x.path("/user/**","/auth/**","user-api/swagger-ui/**").uri("lb://USER-MANAGEMENT"))
				.route(x->x.path("/course/**","course-api/swagger-ui/**").uri("lb://LMS-COURSES"))
				.route(x->x.path("/enrollement/**","enrollement-api/swagger-ui/**").uri("lb://LMS-ENROLLEMENT"))
				.build();
	}

}
