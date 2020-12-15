/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adnan.studentService;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.sampler.SamplerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Bean
    public SamplerAutoConfiguration samplerAutoConfiguration() {
	return new SamplerAutoConfiguration();
    }

    private static final Logger LOG = LogManager.getLogger(StudentController.class.getName());

    private List<Student> students = new ArrayList<>();

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long id) {
        Student student = students.stream().filter(it -> it.getId().equals(id)).findAny().orElse(null);
        if (student != null) {
            LOG.info("Student found : {}", student);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("Student not found with ID : {}", id);
            }
        }
        return student;
    }

    @PostMapping
    public Student add(@RequestBody Student s) {
        s.setId((long) (students.size() + 1));
        students.add(s);
        return s;
    }

}
