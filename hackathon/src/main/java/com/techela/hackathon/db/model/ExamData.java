package com.techela.hackathon.db.model;

public class ExamData{
    String id;
    String title;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getMarksOutOff() {
        return marksOutOff;
    }

    public void setMarksOutOff(String marksOutOff) {
        this.marksOutOff = marksOutOff;
    }

    String subjectTeacher;
    //String marksScored;
    String marksOutOff;
}