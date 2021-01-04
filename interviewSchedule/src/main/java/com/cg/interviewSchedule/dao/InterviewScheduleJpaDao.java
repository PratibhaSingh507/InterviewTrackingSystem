package com.cg.interviewSchedule.dao;

import com.cg.interviewSchedule.model.Interview;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

	public interface InterviewScheduleJpaDao extends JpaRepository<Interview,Integer>{
	    @Query("select i from Interview i where i.email=:email")
	    Optional<Interview> findByEmail(String email);

	}


