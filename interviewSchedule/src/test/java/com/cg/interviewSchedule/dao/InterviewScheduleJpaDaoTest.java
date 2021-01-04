package com.cg.interviewSchedule.dao;



	import com.cg.interviewSchedule.model.Interview;
	import org.junit.Assert;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
	import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
	import org.springframework.test.context.junit4.SpringRunner;

	
	import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

	@RunWith(SpringRunner.class)
	@DataJpaTest
	public class InterviewScheduleJpaDaoTest {

	    @Autowired
	    private InterviewScheduleJpaDao interviewScheduleJpaDao;

	    @Autowired
	    private TestEntityManager testEntityManager;
	    
	    @Test
	    public void testNewInterview() throws Exception{
	    	Interview interview = getInterview();
	    	Interview saveInDb = testEntityManager.persist(interview);
	    	Interview getFromInDb = interviewScheduleJpaDao.findById(saveInDb.getInterviewId()).get();
	        assertThat(getFromInDb).isEqualTo(saveInDb);
	    }

	    @Test
	    public void testGetInterviewById() throws Exception{
	    	
	   	 Interview interview = new Interview();
	        interview.setInterviewId(147926);
	        interview.setInterviewCandidateName("Anurag Kashyap");
	        interview.setInterviewStartTime("10:00AM");
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Mumbai");
	        interview.setEmail("kashyapanurag1995@gmail.com");
	        
	    /*	Interview interview = new Interview();
	        interview.setInterviewCandidateName("Aashish Rautela");
	        interview.setInterviewStartTime("09:00AM");
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Pune");
	        interview.setEmail("aashishrautela@msn.com");
*/
	        //Insert Data into in memory database
	        Interview saveInDb = testEntityManager.persist(interview);
	        //Get Data from DB
	        Interview getInDb = interviewScheduleJpaDao.findById(interview.getInterviewId()).get();
	        assertThat(getInDb).isEqualTo(saveInDb);
	    }

	    @Test
	 
	    	public void testGetAllScheduledInterview() throws Exception{
	    	
		        String URI = "/schedule/getAllInterview";
		        Interview interview1 = new Interview();
		        interview1.setInterviewId(147923);
		        interview1.setInterviewCandidateName("Aman Rathore");
		        interview1.setInterviewStartTime("10:00AM");
		        interview1.setInterviewEndTime("02:00PM");
		        interview1.setInterviewDate(new Date());
		        interview1.setInterviewLocation("Mumbai");
		        interview1.setEmail("rathoreaman1998@gmail.com");

		        Interview interview2 = new Interview();
		        interview2.setInterviewId(147924);
		        interview2.setInterviewCandidateName("Chandrika Shetty");
		        interview2.setInterviewStartTime("08:00AM");
		        interview2.setInterviewEndTime("12:00PM");
		        interview2.setInterviewDate(new Date());
		        interview2.setInterviewLocation("Chennai");
		        interview2.setEmail("chandrikashetty0220@gmail.com");
		        
	        //Save into in memory database
	        testEntityManager.persist(interview1);
	        testEntityManager.persist(interview2);

	        //Retrieve all tickets
	        List<Interview> interviewList = (List<Interview>) interviewScheduleJpaDao.findAll();

	        Assert.assertEquals(2, interviewList.size());
	    }

	    @Test
	    public void testFindByEmail() throws Exception{
	    	
	    	 	Interview interview = new Interview();
	    	 	 interview.setInterviewId(147924);
		        interview.setInterviewCandidateName("Chandrika Shetty");
		        interview.setInterviewStartTime("08:00AM");
		        interview.setInterviewEndTime("12:00PM");
		        interview.setInterviewDate(new Date());
		        interview.setInterviewLocation("Chennai");
		        interview.setEmail("chandrikashetty0221@gmail.com");


		        Interview saveInDb = testEntityManager.persist(interview);
		       Interview getInDb = interviewScheduleJpaDao.findByEmail(saveInDb.getEmail()).get();

	        Assert.assertEquals(saveInDb.getEmail(), getInDb.getEmail());
	    }

	    @Test
	    public void testDeleteInterviewById() throws Exception{
	    	Interview interview1 = new Interview();
	        interview1.setInterviewCandidateName("Tanmay Shah");
	        interview1.setInterviewStartTime("10:00AM");
	        interview1.setInterviewEndTime("12:00PM");
	        interview1.setInterviewDate(new Date());
	        interview1.setInterviewLocation("Chennai");
	        interview1.setEmail("shahtanmay0220@gmail.com");

	        Interview interview2 = new Interview();
	        interview2.setInterviewCandidateName("Aakash chaturvedi");
	        interview2.setInterviewStartTime("08:00AM");
	        interview2.setInterviewEndTime("12:00PM");
	        interview2.setInterviewDate(new Date());
	        interview2.setInterviewLocation("Pune");
	        interview2.setEmail("aakashchaturvedi1220@gmail.com");


	        Interview interview = testEntityManager.persist(interview1);
	        testEntityManager.persist(interview2);

	        //delete one ticket DB
	        testEntityManager.remove(interview);

	        List<Interview> tickets = (List<Interview>) interviewScheduleJpaDao.findAll();
	        Assert.assertEquals(tickets.size(), 1);

	    }

	    @Test
	    public void testUpdateInterview(){

	    /*	Interview interview2 = new Interview();
	        interview2.setInterviewCandidateName("Preet Singh");
	        interview2.setInterviewStartTime("08:00AM");
	        interview2.setInterviewEndTime("12:00PM");
	        interview2.setInterviewDate(new Date());
	        interview2.setInterviewLocation("banglore");
	        interview2.setEmail("singhpreet1999@gmail.com");  */  
	        
	    	 Interview interview2 = new Interview();
		        interview2.setInterviewId(147923);
		        interview2.setInterviewCandidateName("Aman Rathore");
		        interview2.setInterviewStartTime("10:00AM");
		        interview2.setInterviewEndTime("02:00PM");
		        interview2.setInterviewDate(new Date());
		        interview2.setInterviewLocation("Mumbai");
		        interview2.setEmail("rathoreaman1998@gmail.com");


	        testEntityManager.persist(interview2);

	        Optional<Interview> getFromDb = interviewScheduleJpaDao.findByEmail("rathoreaman1998@gmail.com");
	        getFromDb.get().setEmail("rathoreaman2000@gmail.com");
	    //    testEntityManager.persist(getFromDb);

	        assertThat(getFromDb.get().getEmail()).isEqualTo("rathoreaman2000@gmail.com");
	    }


	    private Interview getInterview() {
	    	Interview interview = new Interview();
	    	
	        interview.setInterviewId(147926);
	        interview.setInterviewCandidateName("Anurag Kashyap");
	        interview.setInterviewStartTime("10:00AM");
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Mumbai");
	        interview.setEmail("kashyapanurag1995@gmail.com");

	        return interview;
	    }
	}



