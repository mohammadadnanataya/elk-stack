/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adnan.eclass;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class EClass {
    
    private Long id;
    private String class_name;
    private List<Long> student_id;
    private List<Long> course_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public List<Long> getStudent_id() {
        return student_id;
    }

    public void setStudent_id(List<Long> student_id) {
        this.student_id = student_id;
    }

    public List<Long> getCourse_id() {
        return course_id;
    }

    public void setCourse_id(List<Long> course_id) {
        this.course_id = course_id;
    }
    
}
