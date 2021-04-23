package com.techela.hackathon.db.repository;

import com.techela.hackathon.db.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRespositroy extends JpaRepository<Teacher,Long> {
}
