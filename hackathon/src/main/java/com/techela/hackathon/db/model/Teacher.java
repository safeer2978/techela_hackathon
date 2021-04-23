package com.techela.hackathon.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Teacher {
    @Id
    int id;
    String name;

    public int getId() {
        return id;
    }

    public Teacher() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

/*    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }*/

    String qualification;

  /*  @OneToMany(mappedBy = "subject")
    List<Subject> subjects;*/
}
