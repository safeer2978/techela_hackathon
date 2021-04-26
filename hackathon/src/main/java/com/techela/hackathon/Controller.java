package com.techela.hackathon;

import com.techela.hackathon.db.model.Exam;
import com.techela.hackathon.db.model.Student;
import com.techela.hackathon.db.pdf.Pdf;
import com.techela.hackathon.db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    StudentRepository studentRepository;

    ArrayList<ExamData> data;

    Student prepareExamData(List<Student> students){
        data = new ArrayList<>();
        for (Student student: students
             ) {
            List<Exam> exams= student.getExams();
            if(exams.size()>0){
                for(Exam exam: exams){
                //Exam exam == exams.get(i);
                    ExamData examData = new ExamData();
                    examData.setId(String.valueOf(exam.getId()));
                    examData.setName(exam.getSubject().getName());
                    examData.setSubjectTeacher(exam.getSubject().getName());
                    examData.setTitle(exam.getTitle());
                    data.add(examData);
                }
                return student;
            }
        }
        return null;
    }

    @GetMapping("/generate_html")
    void somethingelse(@RequestParam(name="lang") int lang){
        ArrayList<Student> students= (ArrayList<Student>) studentRepository.findAll();
        Pdf pdf = new Pdf();
        pdf.create1sthtml(students, lang);
    }



    @GetMapping("/1")
    void something() throws IOException {
        ArrayList<Student> students= (ArrayList<Student>) studentRepository.findAll();
        System.out.println(students.get(0).getId());

        Pdf pdf = new Pdf();
        pdf.mainPdf(students);

        //pdf.pdfStudent(prepareExamData(students), data);
        Exam exam=new Exam();
        for(Student S: students){
            if(S.getExams().size()>0){
                exam=S.getExams().get(0);
                break;
            }
        }

        //pdf.mainThird(exam.getSubject().getTeacher(),exam.getSubject(),students);
       // pdf.mainSecond(exam.getSubject().getTeacher());
        //pdf.function();
    };

    @GetMapping("/second_report")
    void something1(@RequestParam(name="lang") int lang) throws IOException {
        ArrayList<Student> students= (ArrayList<Student>) studentRepository.findAll();
        System.out.println(students.get(0).getId());

        Pdf pdf = new Pdf();
       // pdf.mainPdf(students);

        pdf.secondReport(prepareExamData(students), data, lang);
        Exam exam=new Exam();
        for(Student S: students){
            if(S.getExams().size()>0){
                exam=S.getExams().get(0);
                break;
            }
        }

        //pdf.mainThird(exam.getSubject().getTeacher(),exam.getSubject(),students);
        // pdf.mainSecond(exam.getSubject().getTeacher());
        //pdf.function();
    };


    @GetMapping("/third_report")
    void something3(@RequestParam(name="lang") int lang) throws IOException {
        ArrayList<Student> students= (ArrayList<Student>) studentRepository.findAll();
        System.out.println(students.get(0).getId());

        Pdf pdf = new Pdf();
        // pdf.mainPdf(students);

        Exam exam=new Exam();
        for(Student S: students){
            if(S.getExams().size()>0){
                exam=S.getExams().get(0);
                break;
            }
        }

        pdf.thirdReport(exam.getSubject().getTeacher(),exam.getSubject(),students, lang);
        // pdf.mainSecond(exam.getSubject().getTeacher());
        //pdf.function();
    };

    @GetMapping("/3")
    void something2() throws IOException {
        ArrayList<Student> students= (ArrayList<Student>) studentRepository.findAll();
        System.out.println(students.get(0).getId());

        Pdf pdf = new Pdf();
        //pdf.mainPdf(students);

        //pdf.pdfStudent(prepareExamData(students), data);
        Exam exam=new Exam();
        for(Student S: students){
            if(S.getExams().size()>0){
                exam=S.getExams().get(0);
                break;
            }
        }

        pdf.mainThird(exam.getSubject().getTeacher(),exam.getSubject(),students);
        // pdf.mainSecond(exam.getSubject().getTeacher());
        //pdf.function();
    };
}


