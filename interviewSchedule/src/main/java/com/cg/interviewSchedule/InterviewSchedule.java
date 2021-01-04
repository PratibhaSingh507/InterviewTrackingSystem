package com.cg.interviewSchedule;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.cg.interviewSchedule.controller.InterviewScheduleController;




@SpringBootApplication

public class InterviewSchedule {
	
	
	

	
		public static void main(String[] args) { 
			SpringApplication.run( InterviewSchedule.class, args);
			
		
		}
		@Bean
		   public RestTemplate getRestTemplate() {
		      return new RestTemplate();
		   }
	}



