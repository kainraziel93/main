package com.lms.enrollement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.lms.enrollement.buisness.EnrollementServices;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class LmsEnrollmentProgressServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsEnrollmentProgressServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(EnrollementServices services) {
		return args->{
			/*
			services.addEnrollement("achahbar@gmail.com", 1,"2024-01-03");
			services.addEnrollement("bsaili@gmail.com", 1,"2023-11-05");
			services.addEnrollement("achahbar@gmail.com",2,"2023-11-05");
			services.addEnrollement("bsaili@gmail.com", 2,"2023-11-05");
			services.showCourseEnrolledStudents(1);
			services.showStudentCourses("achahbar@gmail.com");
			*/
			
		};
	}

}
