package com.cg.interviewSchedule.services;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.cg.interviewSchedule.exception.ResourceNotFoundException;
import com.cg.interviewSchedule.model.Interview;


	
	public interface InterviewScheduleService {

		Optional<Interview> findInterviewByEmail(String email);
	    Iterable<Interview> getAllInterview();
	    Optional<Interview> findInterviewById(Integer interviewId);
	    Interview updateEmailById(Integer interviewId, String email);
	    boolean deleteInterviewById(Integer interviewId);
	    Interview  createInterview(Interview interview);	   
	   // Optional<Interview> getInterviewByEmail(String email); 
	    
	}



