package com.cg.interviewSchedule.service;



	import com.cg.interviewSchedule.dao.InterviewScheduleJpaDao;
	import com.cg.interviewSchedule.model.Interview;
	import com.cg.interviewSchedule.services.InterviewScheduleService;
	import org.junit.Assert;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.mockito.Mockito;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.mock.mockito.MockBean;
	import org.springframework.test.context.junit4.SpringRunner;


	import java.util.*;

	import static org.assertj.core.api.Assertions.assertThat;

	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class InterviewScheduleServiceTest {

	    @MockBean
	    private InterviewScheduleJpaDao interviewScheduleJpaDao;

	    @Autowired
	    private InterviewScheduleService interviewScheduleService;

	    @Test
	    public void testCreateInterview(){

	        Interview interview = new Interview();
	     interview.setInterviewId(147927);
	        interview.setInterviewCandidateName("priya jiyalal");
	        interview.setInterviewStartTime("10:00AM");   
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Mumbai");
	        interview.setEmail("kpriyajiyalal1995@gmail.com");

	        Mockito.when(interviewScheduleJpaDao.save(interview)).thenReturn(interview);
	        assertThat(interviewScheduleService.createInterview(interview)).isEqualTo(interview);
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
		      //  Interview tt=interviewScheduleJpaDao.findById(147926).get();
		        Interview getInDb = interviewScheduleJpaDao.findById(interview.getInterviewId()).get();
		     
		        //Get Data from DB
		        
		        
		    Mockito.when(getInDb).thenReturn(interview);
	        assertThat(interviewScheduleService.findInterviewById(147926)).isEqualTo(interview);
	    }

	    @Test
	    public void testGetAllScheduledInterview() throws Exception{
	    	 Interview interview1 = new Interview();
		       
		        interview1.setInterviewCandidateName("Aman Rathore");
		        interview1.setInterviewStartTime("10:00AM");
		        interview1.setInterviewEndTime("02:00PM");
		        interview1.setInterviewDate(new Date());
		        interview1.setInterviewLocation("Mumbai");
		        interview1.setEmail("rathoreaman1998@gmail.com");

		        Interview interview2 = new Interview();
		      
		        interview2.setInterviewCandidateName("Chandrika Shetty");
		        interview2.setInterviewStartTime("08:00AM");
		        interview2.setInterviewEndTime("12:00PM");
		        interview2.setInterviewDate(new Date());
		        interview2.setInterviewLocation("Chennai");
		        interview2.setEmail("chandrikashetty0220@gmail.com");

	        List< Interview> interviewList = new ArrayList<>();
	        interviewList.add(interview1);
	        interviewList.add(interview2);

	        Mockito.when(interviewScheduleJpaDao.findAll()).thenReturn(interviewList);
	        assertThat(interviewScheduleService.getAllInterview()).isEqualTo(interviewList);
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
		        interview.setEmail("chandrikashetty0220@gmail.com");
		        
	        Mockito.when(interviewScheduleJpaDao.findByEmail("chandrikashetty0220@gmail.com")).thenReturn(Optional.of(interview));
	        assertThat(interviewScheduleService.findInterviewByEmail("chandrikashetty0220@gmail.com")).isEqualTo(interview);
	    }

	    @Test
	    public void testDeleteInterviewById() throws Exception{
	    	 Interview interview = new Interview();
		        
		        interview.setInterviewId(147926);
		        interview.setInterviewCandidateName("Anurag Kashyap");
		        interview.setInterviewStartTime("10:00AM");
		        interview.setInterviewEndTime("02:00PM");
		        interview.setInterviewDate(new Date());
		        interview.setInterviewLocation("Mumbai");
		        interview.setEmail("kashyapanurag1995@gmail.com");


	        Mockito.when(interviewScheduleJpaDao.save(interview)).thenReturn(interview);
	        Mockito.when(interviewScheduleJpaDao.findById(147926).get()).thenReturn(interview);
	        interviewScheduleJpaDao.deleteById(interview.getInterviewId());
	        Mockito.when(interviewScheduleJpaDao.findById(147926).get()).thenReturn(interview);
	        Assert.assertNotEquals(interview, new Interview());
	        Assert.assertEquals(interviewScheduleService.deleteInterviewById(147926), false);
	    }

	    
	    @Test
	    public void testDeleteInterviewByNull() throws Exception{
	    	Interview interview = new Interview();
	    	interview.setInterviewId(147926);
	        Interview nullInterview = null;
	        Mockito.when(interviewScheduleJpaDao.findById(147926).get()).thenReturn(nullInterview);
	        interviewScheduleJpaDao.deleteById(interview.getInterviewId());
	        Assert.assertEquals(interviewScheduleService.deleteInterviewById(147926), true);
	    }
	    

	    @Test
	    public void testUpdateInterview() throws Exception{

	    	/* Interview interview2 = new Interview();
		        interview2.setInterviewId(147922);
		        interview2.setInterviewCandidateName("Radhika Singh");
		        interview2.setInterviewStartTime("09:00AM");
		        interview2.setInterviewEndTime("01:00PM");
		        interview2.setInterviewDate(new Date());
		        interview2.setInterviewLocation("banglore");
		        interview2.setEmail("singhradhika1999@gmail.com");*/
	    	
	    	 Interview interview2 = new Interview();
		        interview2.setInterviewId(147923);
		        interview2.setInterviewCandidateName("Aman Rathore");
		        interview2.setInterviewStartTime("10:00AM");
		        interview2.setInterviewEndTime("02:00PM");
		        interview2.setInterviewDate(new Date());
		        interview2.setInterviewLocation("Mumbai");
		        interview2.setEmail("rathoreaman1998@gmail.com");



	        Mockito.when(interviewScheduleJpaDao.findById(147923).get()).thenReturn(interview2);
	        interview2.setEmail("rathoreaman1998@gmail.com");
	        Mockito.when(interviewScheduleJpaDao.save(interview2)).thenReturn(interview2);
	        System.out.println(interview2.getEmail());
	        assertThat(interviewScheduleService.updateEmailById(147923, "rathoreaman1998@gmail.com")).isEqualTo(interview2);
	    }

	}


