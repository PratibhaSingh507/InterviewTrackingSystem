package com.cg.interviewSchedule.services.Impl;

import com.cg.interviewSchedule.controller.InterviewScheduleController;
import com.cg.interviewSchedule.dao.InterviewScheduleJpaDao;
import com.cg.interviewSchedule.model.Interview;
import com.cg.interviewSchedule.services.InterviewScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@Transactional
public class InterviewScheduleServiceImpl implements InterviewScheduleService{

	    @Autowired
	    private InterviewScheduleJpaDao interviewScheduleJpaDao;
	    private static final Logger logger = LogManager.getLogger(InterviewScheduleController.class);
		   

	    @Override
	    public Optional<Interview> findInterviewByEmail(String email) {
	    	
	    	logger.info("start with findInterviewByEmail in service");
	    	
	        return interviewScheduleJpaDao.findByEmail(email);
	    }

	    @Override
	    public Iterable<Interview> getAllInterview() {
	    	
	    	logger.info("start with getAllInterview in service");
	    	
	        return interviewScheduleJpaDao.findAll();
	    }

    //Optional --java8  
	    //
	    @Override
	    public Optional<Interview> findInterviewById(Integer interviewId) {
	    	
	    	Interview interview = interviewScheduleJpaDao.findById(interviewId).get();
	    	
	    	logger.info("start with findInterviewById in service");
	    	
	        return  interviewScheduleJpaDao.findById(interviewId);  //Optional 
	    }  

	    @Override
	    public Interview updateEmailById(Integer interviewId, String email) {
	    	Interview interview = interviewScheduleJpaDao.findById(interviewId).get();
	    	interview.setEmail(email);
	    
	    	logger.info("start with updateEmailById in service");
	    	
	        return interviewScheduleJpaDao.save(interview);
	    }

	    @Override
	    public boolean deleteInterviewById(Integer interviewId)  {
	    
	        Interview interview =interviewScheduleJpaDao.findById(interviewId).get();
	        if(null == interview){
	            return false;
	        }
	    	interviewScheduleJpaDao.deleteById(interviewId);
	    	
	    	logger.info("start with deleteInterviewById in service");
	    	
	        return true;
	    }

	    @Override
	    public Interview createInterview(Interview interview) {
	    	logger.info("start with createInterview in service");
	        return interviewScheduleJpaDao.save(interview);
	    }
	}



