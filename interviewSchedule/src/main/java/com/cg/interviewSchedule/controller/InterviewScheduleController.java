package com.cg.interviewSchedule.controller;

import com.cg.interviewSchedule.exception.*;
import com.cg.interviewSchedule.model.Interview;
import com.cg.interviewSchedule.services.InterviewScheduleService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/schedule")
public class InterviewScheduleController {
	
	    @Autowired
	    private InterviewScheduleService interviewScheduleService;
	    
	    private static final Logger logger = LogManager.getLogger(InterviewScheduleController.class);
	   

	 /*   @RequestMapping(value = "/getAllInterview" , method = RequestMethod.GET)
	    public String getAllInterview()
	    {
	    	return "All Interview" ;
	    }
	    */
	    
	    
	    @GetMapping("/getAllInterview")
	    public Iterable getAllInterview(){
	    	logger.info("start with get all interview");	    
	        return interviewScheduleService.getAllInterview();
	    }
	        
	   

	    @GetMapping("/getInterviewById/{interviewId}")
	    public Optional<Interview> getInterviewById(@PathVariable(value= "interviewId") Integer interviewId) throws ResourceNotFoundException {	    
	    	logger.info("start getInterviewById in controller");	    	
			logger.info("start getInterviewById in controller");
	    	interviewScheduleService.findInterviewById(interviewId)
	    	.orElseThrow(() -> new ResourceNotFoundException("Interview not found for this id ::" + interviewId));	    	    
	        return interviewScheduleService.findInterviewById(interviewId);
	    }
	    
	    

	    @GetMapping("/getInterviewByEmail/{email:.+}")
	    public Interview getInterviewByEmail(@PathVariable(value = "email") String email) throws ResourceNotFoundException{	    	
	    	logger.info("start getInterviewByEmail"  + email);	    	
	    	interviewScheduleService.findInterviewByEmail(email)	    
	    	.orElseThrow(() -> new ResourceNotFoundException("Interview not found for this id ::" + email));	    	
	        return interviewScheduleService.findInterviewByEmail(email).get();
	    }
	    
	    

	    @PutMapping("/updateInterviewbyId/{email:.+}/interview/{interviewId}")
	    public Interview updateInterviewById(@PathVariable(value = "email") String email, @PathVariable(value = "interviewId") Integer interviewId) throws ResourceNotFoundException{
	    	logger.info("start getInterviewByEmail : "+ interviewId);
	    	interviewScheduleService.findInterviewById(interviewId)    	
	    	.orElseThrow(() -> new ResourceNotFoundException("Interview not found for this id ::" + interviewId));	    	
	        return interviewScheduleService.updateEmailById(interviewId,email);
	    }
	    
	    
	 /*   @PutMapping("/updateInterviewbyId/{email:.+}/interview/{interviewId}")
	    public ResponseEntity<Interview> updateEmailById(@PathVariable Integer interviewId,@RequestBody String email) throws ResourceNotFoundException{
	    	logger.info("start getInterviewByEmail : "+ interviewId);
	    	interviewScheduleService.findInterviewById(interviewId)    	
	    	.orElseThrow(() -> new ResourceNotFoundException("Interview not found for this id ::" + interviewId));	
	    	Interview interview1=interviewScheduleService.updateEmailById(interviewId, email);
	    	return ResponseEntity.status(HttpStatus.OK).body(interview1);
	        //return interviewScheduleService.updateEmailById(interviewId,email);
	    }*/
	    
	    
	    
	
	    @PostMapping("/createInterview")
	    public Interview createInterview(@RequestBody Interview interview){
	    	logger.info("create method is used: ");	    	
	        return interviewScheduleService.createInterview(interview);
	    }

	    
	    @DeleteMapping("/deleteInterview/{interviewId}")
	    public Map<String, Boolean> deleteInterviewById(@PathVariable("interviewId") Integer interviewId) throws ResourceNotFoundException {
	    	
	    	interviewScheduleService.findInterviewById(interviewId)
	    	.orElseThrow(()-> new ResourceNotFoundException("Interview is not founf for this Id to delete ::" + interviewId));
	    	interviewScheduleService.deleteInterviewById(interviewId);
	    	Map<String, Boolean> response = new HashMap<>();
	    	response.put("deleted", Boolean.TRUE);
	        return response;
	    }

	}



