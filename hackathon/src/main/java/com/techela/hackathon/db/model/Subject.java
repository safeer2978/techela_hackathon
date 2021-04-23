package com.techela.hackathon.db.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Subject {
    @Id
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Subject() {
    }

    public void setName(String name) {
        this.name = name;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name="teacher_id",referencedColumnName="id")
    Teacher teacher;


}
