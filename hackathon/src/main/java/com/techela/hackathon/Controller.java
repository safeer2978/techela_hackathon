package com.techela.hackathon;

import com.techela.hackathon.db.model.Student;
import com.techela.hackathon.db.pdf.Pdf;
import com.techela.hackathon.db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    void something(){
        ArrayList<Student> students= (ArrayList<Student>) studentRepository.findAll();
        System.out.println(students.get(0).getId());

        Pdf pdf = new Pdf();
        pdf.mainPdf(students);
    };
}
