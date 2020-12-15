/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adnan.eclass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.sampler.SamplerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/classes")
public class EClassController {
    
    @Bean
    public SamplerAutoConfiguration samplerAutoConfiguration() {
	return new SamplerAutoConfiguration();
    }
    
    private static final Logger LOG = LogManager.getLogger(EClassController.class.getName());

    private List<EClass> classes = new ArrayList<>();
    
    @Autowired
    StudentInfo studentInfo;
    
    @Autowired
    CourseInfo courseInfo;

    @GetMapping("/{name}")
    public EClassInfo getByName(@RequestParam("name") String name) {
        EClass eclass = classes.stream().filter(it -> it.getClass_name().equals(name)).findAny().orElse(null);
        if (eclass != null) {
            LOG.info("EClass found : {}", eclass);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("EClass not found with Name : {}", name);
            }
        }
        List<Student> students = eclass.getStudent_id().stream()
                .map(id -> {
                    return studentInfo.getStudent(id);
                })
                .collect(Collectors.toList());
        List<Course> courses = eclass.getCourse_id().stream()
                .map(id -> {
                    return courseInfo.getCourse(id);
                })
                .collect(Collectors.toList());
        return new EClassInfo(eclass.getId(), name, students, courses);
    }
    
    @PostMapping
    public EClass add(@RequestBody EClass ec) {
        ec.setId((long) (classes.size() + 1));
        classes.add(ec);
        return ec;
    }

}
