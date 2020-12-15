/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adnan.eclass;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Service
public class CourseInfo {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @HystrixCommand(fallbackMethod = "getFallbackCourse")
    public Course getCourse(Long id) {
        return restTemplate.getForObject("http://course-service/courses/" + id, Course.class);
    }
    
    public Course getFallbackCourse(Long id) {
        Course course = new Course();
        course.setId(1L);
        course.setTitle("DS");
        course.setDescription("Distributed Systems");
        Date date = new Date();
        course.setStart_at(date);
        course.setEnd_at(date);
        course.setLecturer("Disoki");
        return course;
    }
    
}
