/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adnan.eclass;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Service
public class StudentInfo {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @HystrixCommand(fallbackMethod = "getFallbackStudent")
    public Student getStudent(Long id) {
        return restTemplate.getForObject("http://student-service/students/" + id, Student.class);
    }
    
    public Student getFallbackStudent(Long id) {
        Student student = new Student();
        student.setId(1L);
        student.setName("Adnan");
        student.setAge(23);
        student.setGender(Student.Gender.MALE);
        return student;
    }
    
}
