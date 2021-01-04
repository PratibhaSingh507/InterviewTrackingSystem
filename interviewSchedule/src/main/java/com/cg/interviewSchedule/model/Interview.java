package com.cg.interviewSchedule.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "interviewScheduling")

public class Interview {
	
	
	    @Override
		public String toString() {
			return "Interview [interviewId=" + interviewId + ", interviewCandidateName=" + interviewCandidateName + ", interviewStartTime=" + interviewStartTime
					+ ", interviewEndTime =" + interviewEndTime + ", interviewDate=" + interviewDate +  ", interviewLocation=" + interviewLocation + ", email=" + email + "]";
		}

		@Id
	 //   @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "interview_id" , unique = true)
	    private Integer interviewId;
	    @Column(name = "interview_Candidate_Name", nullable = false)
	    private String interviewCandidateName;
	    @Column(name = "interview_Start_Time", nullable = false)
	    private String interviewStartTime;
	    @Column(name = "interview_End_Time", nullable = false)
	    private String interviewEndTime;
	    @Column(name = "interview_Date", nullable = false)
	    private Date interviewDate;
	    @Column(name = "interview_Location", nullable = false)
	    private String interviewLocation;
	    @Column(name = "email", unique = true)
	    private String email;

	    public Integer getInterviewId() {
	        return interviewId;
	    }

	    public void setInterviewId(Integer interviewId) {
	        this.interviewId = interviewId;
	    }

	    public String getInterviewCandidateName() {
	        return interviewCandidateName;
	    }

	    public void setInterviewCandidateName(String interviewCandidateName) {
	        this.interviewCandidateName = interviewCandidateName;
	    }

	    public String getInterviewStartTime() {
	        return interviewStartTime;
	    }

	    public void setInterviewStartTime(String interviewStartTime) {
	        this.interviewStartTime = interviewStartTime;
	    }

	    public String getInterviewEndTime() {
	        return interviewEndTime;
	    }

	    public void setInterviewEndTime(String interviewEndTime) {
	        this.interviewEndTime = interviewEndTime;
	    }

	    public Date getInterviewDate() {
	        return interviewDate;
	    }

	    public void setInterviewDate(Date interviewDate) {
	        this.interviewDate = interviewDate;
	    }

	    public String getInterviewLocation() {
	        return interviewLocation;
	    }

	    public void setInterviewLocation(String interviewLocation) {
	        this.interviewLocation = interviewLocation;
	    }
	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	

	
	}



