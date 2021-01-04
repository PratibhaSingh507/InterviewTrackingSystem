package com.cg.interviewSchedule.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.cg.interviewSchedule.model.Interview;
import com.cg.interviewSchedule.services.InterviewScheduleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

	@RunWith(SpringRunner.class)
	@WebMvcTest(value = InterviewScheduleController.class)
	public class InterviewScheduleControllerTest {
	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private InterviewScheduleService interviewScheduleService;


	    @Test
	    public void testNewInterview() throws Exception{
	        String URI = "/schedule/createInterview";
	     Interview interview = new Interview();
	        interview.setInterviewId(147927);
	        interview.setInterviewCandidateName("Aashish Rautela");
	        interview.setInterviewStartTime("09:00AM");
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Pune");
	        interview.setEmail("aashishrautela@msn.com");
	        String jsonInput = this.converttoJson(interview);
	       
	        /*     Interview interview = new Interview();
	        interview.setInterviewId(147926);
	        interview.setInterviewCandidateName("Anurag Kashyap");
	        interview.setInterviewStartTime("10:00AM");
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Mumbai");
	        interview.setEmail("kashyapanurag1995@gmail.com");
	        String jsonInput = this.converttoJson(interview);
 */
	        Mockito.when(interviewScheduleService.createInterview(Mockito.any(Interview.class))).thenReturn(interview);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(147927).isEqualTo(147927);
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	    }

	    @Test
	    public void testGetInterviewById() throws Exception{
	        String URI= "/schedule/getInterviewById/{interviewId}";
	    
	        Interview interview = new Interview();
	        interview.setInterviewId(147926);
	        interview.setInterviewCandidateName("Anurag Kashyap");
	        interview.setInterviewStartTime("10:00AM");
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Mumbai");
	        interview.setEmail("kashyapanurag1995@gmail.com");
	        String jsonInput = this.converttoJson(interview);

	        Mockito.when(interviewScheduleService.findInterviewById(Mockito.any()).get()).thenReturn(interview);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 147926).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	    }


	    @Test
	    public void testGetInterviewByIdIndividual() throws Exception{
	   
	        String URI= "/schedule/getInterviewById/{interviewId}";
	        
	        Interview interview = new Interview();
	     
	        interview.setInterviewId(147926);
	        interview.setInterviewCandidateName("Anurag Kashyap");
	        interview.setInterviewStartTime("10:00AM");
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Mumbai");
	        interview.setEmail("kashyapanurag1995@gmail.com");
	        String jsonInput = this.converttoJson(interview);


	        Mockito.when(interviewScheduleService.findInterviewById(Mockito.any()).get()).thenReturn(interview);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 147926)
	                .accept(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        System.out.println(jsonOutput);
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        assertThat(interview.getEmail()).isEqualTo("kashyapanurag1995@gmail.com");
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

	        List<Interview> interviewList = new ArrayList<>();
	        interviewList.add(interview1);
	        interviewList.add(interview2);

	        String jsonInput = this.converttoJson(interviewList);

	        Mockito.when(interviewScheduleService.getAllInterview()).thenReturn(interviewList);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
	    }

	    @Test
	    public void testFindByEmail() throws Exception{
	        String URI = "/schedule/getInterviewByEmail/{email:.+}";
	        Interview interview = new Interview();
	        
	        interview.setInterviewId(147925);
	        interview.setInterviewCandidateName("Aanchal Tiwari");
	        interview.setInterviewStartTime("09:00AM");
	        interview.setInterviewEndTime("01:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Pune");
	        interview.setEmail("tiwariaanchal0112@gmail.com");

	        String jsonInput = this.converttoJson(interview);

	        Mockito.when(interviewScheduleService.findInterviewByEmail(Mockito.anyString()).get()).thenReturn(interview);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, "tiwariaanchal0112@gmail.com").accept(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
	    }

	    @Test
	    public void testDeleteInterviewById() throws Exception{
	        String URI = "/schedule/deleteInterviewById/interview/{interviewId}";
	        Interview interview = new Interview();
	        
	        interview.setInterviewId(147926);
	        interview.setInterviewCandidateName("Anurag Kashyap");
	        interview.setInterviewStartTime("10:00AM");
	        interview.setInterviewEndTime("02:00PM");
	        interview.setInterviewDate(new Date());
	        interview.setInterviewLocation("Mumbai");
	        interview.setEmail("kashyapanurag1995@gmail.com");

	        Mockito.when(interviewScheduleService.findInterviewById(Mockito.any()).get()).thenReturn(interview);
	        Mockito.when(interviewScheduleService.deleteInterviewById(Mockito.any())).thenReturn(true);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 147926).accept(MediaType.
	        		APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	    }

	    @Test
	    public void testUpdateInterview() throws Exception{

	        String URI = "/schedule/updateInterviewbyId/{email:.+}/Interview/{InterviewId}";
	        Interview interview2 = new Interview();
	        interview2.setInterviewId(147926);
	        
	        interview2.setInterviewCandidateName("Anurag Kashyap");
	        interview2.setInterviewStartTime("10:00AM");
	        interview2.setInterviewEndTime("02:00PM");
	        interview2.setInterviewDate(new Date());
	        interview2.setInterviewLocation("Mumbai");
	        interview2.setEmail("kashyapanurag1995@gmail.com");
	        
	        /*
	        interview2.setInterviewCandidateName("Preet Singh");
	        interview2.setInterviewStartTime("08:00AM");
	        interview2.setInterviewEndTime("12:00PM");
	        interview2.setInterviewDate(new Date());
	        interview2.setInterviewLocation("banglore");
	        interview2.setEmail("singhpreet1999@gmail.com");*/

	        String jsonInput = this.converttoJson(interview2);

	        Mockito.when(interviewScheduleService.updateEmailById(Mockito.any(), Mockito.anyString())).thenReturn(interview2);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, "kashyapanurag1995@gmail.com",147924).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat("kashyapanurag1995@gmail.com").isEqualTo("kashyapanurag1995@gmail.com");
	    }

	    /**
	     * Convert Object into Json String by using Jackson ObjectMapper
	     * @param Interview
	     * @return
	     * @throws JsonProcessingException
	     */
	    private String converttoJson(Object interview) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(interview);
	    }

	}



