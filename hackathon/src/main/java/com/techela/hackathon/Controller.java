package com.techela.hackathon;

import com.techela.hackathon.db.model.Student;
import com.techela.hackathon.db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    void something(){
        List<Student> students= studentRepository.findAll();
        System.out.println(students.get(0).getId());
    };
}
