package com.lms.course;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.lms.course.buisness.CourseServices;
import com.lms.course.entity.Course;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LmsCourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsCourseServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(CourseServices services) {
		return args ->{
			Course course1 = new Course("informatique","description du cours de l informatique ",LocalDate.parse("2023-01-01"),LocalDate.parse("2024-07-01"));
			Course course2 = new Course("algebre lineare","description du cours du mathematique algebre lineaire ",LocalDate.parse("2023-10-01"),LocalDate.parse("2024-01-01"));
			Course course3 = new Course("phisyque","description du cours du physique ",LocalDate.parse("2023-07-01"),LocalDate.parse("2023-09-01"));
			Course course4 = new Course("analyze numerique","description du cours du mathematique ",LocalDate.parse("2024-01-01"),LocalDate.parse("2024-01-06"));
			services.addCourse(course1);
			services.addCourse(course2);
			services.addCourse(course3);	
			services.addCourse(course4);
		};
	}
}
