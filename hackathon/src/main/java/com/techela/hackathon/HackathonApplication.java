package com.techela.hackathon;

import com.techela.hackathon.db.model.Student;
import com.techela.hackathon.db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HackathonApplication {



	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);

	}

}
