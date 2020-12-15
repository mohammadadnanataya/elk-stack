/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adnan.courseService;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.sleuth.sampler.SamplerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/courses")
public class CourseController {
    
    @Bean
    public SamplerAutoConfiguration samplerAutoConfiguration() {
	return new SamplerAutoConfiguration();
    }
    
    private static final Logger LOG = LogManager.getLogger(CourseController.class.getName());
    
    private List<Course> courses = new ArrayList<>();
    
    @GetMapping("/{id}")
    public Course getById(@PathVariable("id") Long id) {
        Course course = courses.stream().filter(it -> it.getId().equals(id)).findAny().orElse(null);
        if (course != null) {
            LOG.info("Course found : {}", course);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("Course not found with ID : {}", id);
            }
        }
        return course;
    }
    
    @PostMapping
    public Course add(@RequestBody Course c) {
        c.setId((long) (courses.size() + 1));
        courses.add(c);
        return c;
    }
    
}
