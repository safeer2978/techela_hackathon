package com.techela.hackathon.db.repository;

import com.techela.hackathon.db.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRespository extends JpaRepository<Exam,Long> {
}
